package com.assignment.libraryJdbc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.assignment.libraryJdbc.entities.Book;
import com.assignment.libraryJdbc.entities.Subject;

public class SubjectProcessor extends Processor {


	public static void searchSubject(Scanner scanner) {
		System.out.println("enter the subject name to search");
		String subjectName = scanner.next();
		boolean foundSubject = false;
		List<Subject> subjects = SubjectProcessor.getAllSubjects();
		

		if (!foundSubject) {
			System.out.println("subject not found with this subtitle: " + subjectName + ". Please modify your search.");
		}

	}

	public static void addSubject(Scanner scanner) {
		/*if(allBooks.isEmpty()) {
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
				if(bookIdToAdd == book.getBookId()) {
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
				
		List<Subject> subjects = SubjectProcessor.getAllSubjects();
		subjects.add(subject);
		SubjectProcessor.writeSubjectsToFile(subjects);
		System.out.println("Subject added");*/
	}

	@SuppressWarnings("unchecked")
	public static List<Subject> getAllSubjects() {
		List<Subject> subjects = new ArrayList<Subject>();
		/*FileInputStream fis = null;
		ObjectInputStream ois = null;
		
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
		}*/
		return subjects;
	}

	public static void deleteSubject(Scanner scanner) {
		System.out.println("enter the subject name to delete");
		String subjectName = scanner.next();
		boolean subjectFoundForDeletion = false;
		Subject subjectForDeletion = null;
		List<Subject> subjects = getAllSubjects();
		for (Subject subject : subjects) {
			if(subject.getSubtitle().equalsIgnoreCase(subjectName)) {
				System.out.println("deleting subject: "+subject.toString());
				subjectForDeletion = subject;
				subjectFoundForDeletion = true;
				break;
			}
		}
		if(subjectFoundForDeletion) {
			subjects.remove(subjectForDeletion);
		}
		else {
			System.out.println("No subject found with the name: "+subjectName+" for deletion.");
		}
	}

	/**
	 * @param bookForDeletion
	 */
	static void removeRefSubjects(Book bookForDeletion) {
		List<Subject> subjects = getAllSubjects();
		for (Subject subject : subjects) {
			Set<Book> references = subject.getReferences();
			if(references.size() == 1) {
				if(references.contains(bookForDeletion)) {
					subjects.remove(subject);
				}
			}
			else {
				references.remove(bookForDeletion);
			}
			
		}
	}
}
