package com.assignment.libraryhibernate.hb;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.assignment.libraryhibernate.entities.Subject;
import com.assignment.libraryhibernate.util.HibernateUtil;

/**
 * Home object for domain model class Subject.
 * @see .Subject
 */
public class SubjectDAO {

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
}
