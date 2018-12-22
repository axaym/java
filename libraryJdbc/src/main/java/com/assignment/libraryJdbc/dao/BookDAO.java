package com.assignment.libraryJdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.assignment.libraryJdbc.entities.Book;
import com.assignment.libraryJdbc.util.ConnectionFactory;

public class BookDAO implements IBookDAO {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.libraryJdbc.dao.IBookDAO#searchBook(java.lang.String)
	 */
	public List<Book> searchBook(String bookName) {
		Connection connection = ConnectionFactory.getConnection();
		List<Book> books = new ArrayList<Book>();
		try {
			
			String query = "select book_id, title, price, volume, publish_date, subject_id from book where title like ?";
			PreparedStatement ps = connection.prepareStatement(
					query);
			ps.setString(1, "%" + bookName + "%");
			getBookResults(books, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	/**
	 * @param books
	 * @param ps
	 * @throws SQLException
	 */
	private void getBookResults(List<Book> books, PreparedStatement ps) throws SQLException {
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Book book = new Book();
			book.setBookId(rs.getLong("book_id"));
			book.setTitle(rs.getString("title"));
			book.setPrice(rs.getDouble("price"));
			book.setVolume(rs.getInt("volume"));
			book.setPublishDate(rs.getDate("publish_date"));
			book.setSubjectId(rs.getLong("subject_id"));
			books.add(book);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.libraryJdbc.dao.IBookDAO#addBook(com.assignment.libraryJdbc.
	 * entities.Book)
	 */
	public boolean addBook(Book book) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO book " + "(title,price,volume,publish_date,subject_id) " + "VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, book.getTitle());
			ps.setDouble(2, book.getPrice());
			ps.setInt(3, book.getVolume());
			ps.setDate(4, new Date(book.getPublishDate().getTime()));
			ps.setLong(5, book.getSubjectId());

			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.libraryJdbc.dao.IBookDAO#deleteBook(long)
	 */
	public boolean deleteBook(long bookId) {
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection.prepareStatement("delete from book where book_id = ?");
			ps.setLong(1, bookId);
			int i = ps.executeUpdate();
			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public List<Book> getBooks() {
		Connection connection = ConnectionFactory.getConnection();
		List<Book> books = new ArrayList<Book>();
		try {
			
			String query = "select book_id, title, price, volume, publish_date, subject_id from book";
			PreparedStatement ps = connection.prepareStatement(
					query);
			getBookResults(books, ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

}
