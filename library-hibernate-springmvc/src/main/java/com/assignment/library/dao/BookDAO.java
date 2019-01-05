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

import com.assignment.library.entities.Book;

/**
 * Home object for domain model class Book.
 * @see .Book
 */
@Repository("bookDAO")
@Transactional
public class BookDAO implements IBookDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.assignment.library.dao.IBookDAO#persist(com.assignment.library.entities.Book)
	 */
	@Override
	public void persist(Book transientInstance) {
		System.out.println("persisting Book instance");
		Session session = sessionFactory.getCurrentSession();
		try {
			
			session.persist(transientInstance);
			System.out.println("persist successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
		finally {
			
		}
	}
	
	/* (non-Javadoc)
	 * @see com.assignment.library.dao.IBookDAO#delete(com.assignment.library.entities.Book)
	 */
	@Override
	public void delete(Book persistentInstance) {
		System.out.println("deleting Book instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			System.out.println("delete successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.assignment.library.dao.IBookDAO#deleteById(java.lang.Integer)
	 */
	@Override
	public void deleteById(Integer bookId) {
		System.out.println("deleting Book instance by bookId");
		Session session = sessionFactory.getCurrentSession();
		try {
			
			
			session.createQuery("delete from Book where book_id = :id")
			  .setParameter("id", bookId)
			  .executeUpdate();
			
			System.out.println("delete successful");
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw re;
		}
		finally {
			
		}
	}

	/* (non-Javadoc)
	 * @see com.assignment.library.dao.IBookDAO#findById(java.lang.Integer)
	 */
	@Override
	public Book findById(java.lang.Integer id) {
		System.out.println("getting Book instance with id: " + id);
		Session session = sessionFactory.getCurrentSession();
		try {
		    
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
		}
		finally {
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.assignment.library.dao.IBookDAO#searchBooks(java.lang.String)
	 */
	@Override
	public List<Book> searchBooks(String partialTitle) {
		System.out.println("getting Book by partial Title: " + partialTitle);
		try {
			Session session = sessionFactory.getCurrentSession();
			
			CriteriaBuilder builder = session.
					getCriteriaBuilder();
			
			CriteriaQuery<Book> query = builder.createQuery(Book.class);
			Root<Book> root = query.from(Book.class);
			query.select(root).where(builder.like(root.get("title"), "%"+partialTitle+"%"));
			Query<Book> q = sessionFactory.getCurrentSession().
					createQuery("select b from Book b where b.title like :title").setParameter("title", "%"+partialTitle+"%");
			List<Book> books = q.getResultList();
			//"select b from Book b join Subject s on s.subjectId = b.subjectId where b.title like :title"
			
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
