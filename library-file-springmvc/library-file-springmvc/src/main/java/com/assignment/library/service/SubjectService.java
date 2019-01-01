package com.assignment.library.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.library.entities.Book;
import com.assignment.library.entities.Subject;

@Service("subjectService")
public class SubjectService extends BaseService implements ISubjectService {

	private final String SUBJECTS_TXT = "subjects.txt";

	@Autowired
	private IBookService bookService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.ISubjectService#searchSubjects(com.assignment.
	 * library.entities.Subject)
	 */
	public List<Subject> searchSubjects(Subject subject) {
		System.out.println("enter the subject name to search");
		String subjectName = subject.getSubtitle();
		List<Subject> subjectsResult = new ArrayList<>();
		List<Subject> subjects = getAllSubjects();
		for (Subject subject2 : subjects) {
			if (subject2.getSubtitle().contains(subjectName)) {
				subjectsResult.add(subject2);
			}
		}
		return subjectsResult;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.ISubjectService#addSubject(com.assignment.
	 * library.entities.Subject)
	 */
	public String addSubject(Subject subject) {
		List<Book> allBooks = bookService.getAllBooks();
		if (allBooks.isEmpty()) {
			return "You donot have any books. Please add books first.";
		}

		subject.setSubjectId(getId());

		List<Subject> subjects = getAllSubjects();
		subjects.add(subject);
		writeSubjectsToFile(subjects);
		return "success";
	}

	private void writeSubjectsToFile(List<Subject> subjects) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			File newFile = getFile(SUBJECTS_TXT);

			fos = new FileOutputStream(newFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(subjects);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (oos != null) {
					oos.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.assignment.library.service.ISubjectService#getAllSubjects()
	 */
	@SuppressWarnings("unchecked")
	public List<Subject> getAllSubjects() {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			File newFile = getFile(SUBJECTS_TXT);

			fis = new FileInputStream(newFile);
			ois = new ObjectInputStream(fis);
			subjects = (List<Subject>) ois.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return subjects;
		} catch (IOException e) {
			e.printStackTrace();
			return subjects;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return subjects;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.ISubjectService#deleteSubject(com.assignment.
	 * library.entities.Subject)
	 */
	public String deleteSubject(Subject subject) {
		System.out.println("enter the subject name to delete");
		long subjectId = subject.getSubjectId();
		boolean subjectFoundForDeletion = false;
		Subject subjectForDeletion = null;
		List<Subject> subjects = getAllSubjects();
		for (Subject subject2 : subjects) {
			if (subject2.getSubjectId() == subjectId) {
				System.out.println("deleting subject: " + subject2.toString());
				subjectForDeletion = subject2;
				subjectFoundForDeletion = true;
				break;
			}
		}
		if (subjectFoundForDeletion) {
			subjects.remove(subjectForDeletion);
			writeSubjectsToFile(subjects);
		} else {
			return "No subject found with the name: " + subjectId + " for deletion.";
		}

		// delete related book entries
		bookService.removeRefBooks(subjectForDeletion);
		return "success";
	}
}
