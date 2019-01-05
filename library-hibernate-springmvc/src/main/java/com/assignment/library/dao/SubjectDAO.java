package com.assignment.library.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.assignment.library.entities.Subject;

/**
 * Home object for domain model class Subject.
 * @see .Subject
 */
@Repository("subjectDAO")
@Transactional
public class SubjectDAO implements ISubjectDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.assignment.library.dao.ISubjectDAO#persist(com.assignment.library.entities.Subject)
	 */
	@Override
	public void persist(Subject transientInstance) {
		System.out.println("persisting Subject instance");
		Session session = sessionFactory.getCurrentSession();
		try {
			
			session.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			System.out.println("persist failed");
			throw re;
		}
		finally {
			
		}
	}
	
	/* (non-Javadoc)
	 * @see com.assignment.library.dao.ISubjectDAO#deleteById(java.lang.Integer)
	 */
	@Override
	public void deleteById(Integer subjectId) {
		System.out.println("deleting Subject instance by SubjectId");
		Session session = sessionFactory.getCurrentSession();
		try {			
			
			session.createQuery("delete from Subject where subject_id = :id")
			  .setParameter("id", subjectId)
			  .executeUpdate();
			
			System.out.println("delete successful");
		} catch (RuntimeException re) {
			System.out.println("delete failed");
			throw re;
		}
		finally {
			
		}
	}	

	/* (non-Javadoc)
	 * @see com.assignment.library.dao.ISubjectDAO#findById(java.lang.Integer)
	 */
	@Override
	public Subject findById(java.lang.Integer id) {
		System.out.println("getting Subject instance with id: " + id);
		Session session = sessionFactory.getCurrentSession();
		try {
			
			CriteriaBuilder builder = session.
					getCriteriaBuilder();
			
			CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
			Root<Subject> root = query.from(Subject.class);
			query.select(root).where(builder.equal(root.get("subjectId"), id));
			Query<Subject> q = sessionFactory.getCurrentSession().createQuery(query);
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
			
		}
	}

	/* (non-Javadoc)
	 * @see com.assignment.library.dao.ISubjectDAO#searchSubjects(java.lang.String)
	 */
	@Override
	public List<Subject> searchSubjects(String partialTitle) {
		System.out.println("getting Subject by partial subTitle: " + partialTitle);
		Session session = sessionFactory.getCurrentSession();
		try {
			
			CriteriaBuilder builder = session.
					getCriteriaBuilder();
			
			CriteriaQuery<Subject> query = builder.createQuery(Subject.class);
			//Root<Subject> root = query.from(Subject.class);
			//query.select(root).where(builder.like(root.get("subtitle"), "%"+partialTitle+"%"));
			Query<Subject> q = sessionFactory.getCurrentSession().createQuery("select s from Subject s where s.subtitle like :subtitle").
					setParameter("subtitle", "%"+partialTitle+"%");
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
			
		}
	}
}
