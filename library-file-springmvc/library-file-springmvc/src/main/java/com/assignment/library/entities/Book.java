package com.assignment.library.entities;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -305095379149392085L;

	private long bookId;
	
	private String title;
	
	private double price; 
	
	private int volume; 
	 
	private Date publishDate;
	
	private long subjectId;

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	} 
	
	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		return "title:" + title + "\nprice: " + price + "\nvolume: " + volume
				+ "\npublishDate: " + publishDate
				+ "\nbookId: " + bookId
				+ "\nsubjectId: " + subjectId;		
	}
		
}
