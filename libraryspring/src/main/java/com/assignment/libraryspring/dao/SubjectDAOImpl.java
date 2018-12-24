package com.assignment.libraryspring.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.assignment.libraryspring.entities.Subject;
import com.assignment.libraryspring.util.HibernateUtil;

/**
 * Home object for domain model class Subject.
 * @see .Subject
 */
@Repository("subjectDAO")
public class SubjectDAOImpl implements ISubjectDAO {

	/* (non-Javadoc)
	 * @see com.assignment.libraryhibernate.dao.ISubjectDAO#persist(com.assignment.libraryhibernate.entities.Subject)
	 */
	public void persist(Subject transientInstance) {
		System.out.println("persisting Subject instance");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			throw re;
		}
		finally {
			session.close();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.assignment.libraryhibernate.dao.ISubjectDAO#deleteById(java.lang.Integer)
	 */
	public void deleteById(Integer subjectId) {
		System.out.println("deleting Subject instance by SubjectId");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			session.createQuery("delete from Subject where subject_id = :id")
			  .setParameter("id", subjectId)
			  .executeUpdate();
			
			System.out.println("delete successful");
		} catch (RuntimeException re) {
			System.out.println("delete failed");
			throw re;
		}
		finally {
			session.close();
		}
	}	

	/* (non-Javadoc)
	 * @see com.assignment.libraryhibernate.dao.ISubjectDAO#findById(java.lang.Integer)
	 */
	public Subject findById(java.lang.Integer id) {
		System.out.println("getting Subject instance with id: " + id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.
					getCriteriaBuilder();
			
			CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
			Root<Subject> root = query.from(Subject.class);
			query.select(root).where(builder.equal(root.get("subjectId"), id));
			Query<Subject> q = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(query);
			Subject Subject = q.getSingleResult();
			if (Subject == null) {
				System.out.println("get successful, no instance found");
			} else {
				System.out.println("get successful, instance found");
			}
			return Subject;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			throw re;
		}
		finally {
			session.close();
		}
	}

	/* (non-Javadoc)
	 * @see com.assignment.libraryhibernate.dao.ISubjectDAO#searchSubjects(java.lang.String)
	 */
	public List<Subject> searchSubjects(String partialTitle) {
		System.out.println("getting Subject by partial subTitle: " + partialTitle);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			CriteriaBuilder builder = session.
					getCriteriaBuilder();
			
			CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
			Root<Subject> root = query.from(Subject.class);
			query.select(root).where(builder.like(root.get("subtitle"), "%"+partialTitle+"%"));
			Query<Subject> q = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(query);
			List<Subject> Subjects = q.getResultList();
			if (Subjects == null) {
				System.out.println("get successful, no instance found");
			} else {
				System.out.println("get successful, instance found");
			}
			return Subjects;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			throw re;
		}
		finally {
			session.close();
		}
	}

	@Override
	public List<Subject> getSubjects() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();		
			
			Query<Subject> q = session.createQuery("from Subject");
			List<Subject> Subjects = q.getResultList();
			if (Subjects == null) {
				System.out.println("get successful, no instance found");
			} else {
				System.out.println("get successful, instance found");
			}
			return Subjects;
		} catch (RuntimeException re) {
			System.out.println("get failed");
			throw re;
		}
		finally {
			session.close();
		}
	}
}
