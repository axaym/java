package com.assignment.library.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
	 * @see com.assignment.library.service.ISubjectService#searchSubject(java.util.
	 * Scanner)
	 */
	public void searchSubject(Scanner scanner) {
		System.out.println("enter the subject name to search");
		String subjectName = scanner.next();
		boolean foundSubject = false;
		List<Subject> subjects = getAllSubjects();
		for (Subject subject : subjects) {
			if (subject.getSubtitle().contains(subjectName)) {
				System.out.println("found subject: " + subject.toString());
				foundSubject = true;
				break;
			}
		}

		if (!foundSubject) {
			System.out.println("subject not found with this subtitle: " + subjectName + ". Please modify your search.");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.assignment.library.service.ISubjectService#addSubject(java.util.Scanner)
	 */
	public void addSubject(Scanner scanner) {
		List<Book> allBooks = bookService.getAllBooks();
		if (allBooks.isEmpty()) {
			System.out.println("You donot have any books. Please add books first.");
			return;
		}
		System.out.println("Enter bookIds (comma separated) to associate with this subject for this list");
		for (Book book : allBooks) {
			System.out.println("****************************");
			System.out.println(book.toString());
			System.out.println("****************************");
		}

		Subject subject = new Subject();
		Set<Book> references = new HashSet<Book>();
		String refStr = scanner.next();
		String[] values = refStr.split(",");
		for (String str : values) {
			long bookIdToAdd = Long.parseLong(str);
			for (Book book : allBooks) {
				if (bookIdToAdd == book.getBookId()) {
					references.add(book);
					break;
				}
			}
		}
		subject.setReferences(references);

		System.out.println("Enter subject sub title");
		subject.setSubtitle(scanner.next());
		System.out.println("Enter subject duration in hours");
		subject.setDurationInHours(scanner.nextInt());
		subject.setSubjectId(getId());

		List<Subject> subjects = getAllSubjects();
		subjects.add(subject);
		writeSubjectsToFile(subjects);
		System.out.println("Subject added");
	}

	private void writeSubjectsToFile(List<Subject> subjects) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(new File(SUBJECTS_TXT));
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
			File newFile = new File(SUBJECTS_TXT);
			newFile.createNewFile();

			if (isFileEmpty(SUBJECTS_TXT)) {
				return subjects;
			}

			fis = new FileInputStream(new File(SUBJECTS_TXT));
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
	 * @see com.assignment.library.service.ISubjectService#deleteSubject(java.util.
	 * Scanner)
	 */
	public void deleteSubject(Scanner scanner) {
		System.out.println("enter the subject name to delete");
		String subjectName = scanner.next();
		boolean subjectFoundForDeletion = false;
		Subject subjectForDeletion = null;
		List<Subject> subjects = getAllSubjects();
		for (Subject subject : subjects) {
			if (subject.getSubtitle().equalsIgnoreCase(subjectName)) {
				System.out.println("deleting subject: " + subject.toString());
				subjectForDeletion = subject;
				subjectFoundForDeletion = true;
				break;
			}
		}
		if (subjectFoundForDeletion) {
			subjects.remove(subjectForDeletion);
			writeSubjectsToFile(subjects);
		} else {
			System.out.println("No subject found with the name: " + subjectName + " for deletion.");
		}
	}

	/**
	 * @param bookForDeletion
	 */
	public void removeRefSubjects(Book bookForDeletion) {
		List<Subject> subjects = getAllSubjects();
		for (Subject subject : subjects) {
			Set<Book> references = subject.getReferences();
			if (references.size() == 1) {
				if (references.contains(bookForDeletion)) {
					subjects.remove(subject);
				}
			} else {
				references.remove(bookForDeletion);
			}

		}
		writeSubjectsToFile(subjects);
	}
}
