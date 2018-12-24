package com.assignment.libraryspring.dao;

import java.util.List;

import com.assignment.libraryspring.entities.Subject;

public interface ISubjectDAO {

	public void persist(Subject transientInstance);

	public void deleteById(Integer subjectId);

	public Subject findById(java.lang.Integer id);

	public List<Subject> searchSubjects(String partialTitle);

	public List<Subject> getSubjects();

}