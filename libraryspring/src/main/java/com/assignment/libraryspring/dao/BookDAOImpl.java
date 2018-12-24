package com.assignment.libraryspring.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.assignment.libraryspring.entities.Book;
import com.assignment.libraryspring.util.HibernateUtil;

/**
 * Home object for domain model class Book.
 * 
 * @see .Book
 */
@Repository("bookDAO")
public class BookDAOImpl implements IBookDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.libraryhibernate.dao.IBookDAO#persist(com.assignment.
	 * libraryhibernate.entities.Book)
	 */

	public void persist(Book transientInstance) {
		System.out.println("persisting Book instance");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		} finally {
			session.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.libraryhibernate.dao.IBookDAO#delete(com.assignment.
	 * libraryhibernate.entities.Book)
	 */

	public void delete(Book persistentInstance) {
		System.out.println("deleting Book instance");
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().delete(persistentInstance);
			System.out.println("delete successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.libraryhibernate.dao.IBookDAO#deleteById(java.lang.Integer)
	 */
	public void deleteById(Integer bookId) {
		System.out.println("deleting Book instance by bookId");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

			session.createQuery("delete from Book where book_id = :id").setParameter("id", bookId).executeUpdate();

			System.out.println("delete successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		} finally {
			session.close();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.libraryhibernate.dao.IBookDAO#findById(java.lang.Integer)
	 */

	public Book findById(java.lang.Integer id) {
		System.out.println("getting Book instance with id: " + id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Book instance = (Book) session.get("Book", id);
			if (instance == null) {
				System.out.println("get successful, no instance found");
			} else {
				System.out.println("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		} finally {
			session.close();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.libraryhibernate.dao.IBookDAO#searchBooks(java.lang.String)
	 */

	public List<Book> searchBooks(String partialTitle) {
		System.out.println("getting Book by partial Title: " + partialTitle);
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();

			CriteriaQuery<Book> query = builder.createQuery(Book.class);
			Root<Book> root = query.from(Book.class);
			query.select(root).where(builder.like(root.get("title"), "%" + partialTitle + "%"));
			Query<Book> q = HibernateUtil.getSessionFactory().getCurrentSession().createQuery(query);
			List<Book> books = q.getResultList();
			session.close();
			if (books == null) {
				System.out.println("get successful, no instance found");
			} else {
				System.out.println("get successful, instance found");
			}
			return books;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}

	@Override
	public List<Book> getBooks() {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.beginTransaction();
			Query<Book> q = session.createQuery("from Book");
			List<Book> books = q.getResultList();
			session.close();
			if (books == null) {
				System.out.println("get successful, no instance found");
			} else {
				System.out.println("get successful, instance found");
			}
			return books;
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}

	}

}
