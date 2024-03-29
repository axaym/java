package com.assignment.library.entities;
// default package
// Generated Dec 23, 2018 8:35:19 PM by Hibernate Tools 5.2.10.Final

import java.math.BigDecimal;
import java.util.Date;

/**
 * Book generated by hbm2java
 */
public class Book implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9189279053232964271L;
	private Integer bookId;
	private Integer subjectId;
	
	private Subject subject;
	private String title;
	private BigDecimal price;
	private int volume;
	private Date publishDate;

	public Book() {
	}

	public Book(Integer subjectId, Subject subject, String title, BigDecimal price, int volume, Date publishDate) {
		this.subjectId = subjectId;
		this.subject = subject;
		this.title = title;
		this.price = price;
		this.volume = volume;
		this.publishDate = publishDate;
	}

	public Integer getBookId() {
		return this.bookId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getVolume() {
		return this.volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	
	
	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "title:" + title + "\nprice: " + price + "\nvolume: " + volume
				+ "\npublishDate: " + publishDate
				+ "\nbookId: " + bookId;
	}
}
