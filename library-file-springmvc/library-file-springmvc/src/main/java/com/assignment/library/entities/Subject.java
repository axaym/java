package com.assignment.library.entities;

import java.io.Serializable;

public class Subject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -238459049778244120L;

	private long subjectId;

	private String subtitle;

	private int durationInHours;

	/*private Set<Book> references;*/

	public long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public int getDurationInHours() {
		return durationInHours;
	}

	public void setDurationInHours(int durationInHours) {
		this.durationInHours = durationInHours;
	}

	/*public Set<Book> getReferences() {
		return references;
	}

	public void setReferences(Set<Book> references) {
		this.references = references;
	}*/

	@Override
	public String toString() {
		/*String booksStr = "\nbookIds: ";
		for (Book book : references) {
			booksStr += book.getBookId();
			booksStr += "\n";
		}*/
		return "subtitle:" + subtitle + "\ndurationInHours: " 
			+ durationInHours + "\nsubjectId: " + subjectId
				/*+ booksStr*/;
	}

}
