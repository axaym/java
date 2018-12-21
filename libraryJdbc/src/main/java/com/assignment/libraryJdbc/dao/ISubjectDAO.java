package com.assignment.libraryJdbc.dao;

import java.util.List;

import com.assignment.libraryJdbc.entities.Subject;

public interface ISubjectDAO {
	
	public List<Subject> searchSubject(String subjectName);
	
	public boolean addSubject(Subject subject);
	
	public boolean deleteSubject(long subjectId);

}
