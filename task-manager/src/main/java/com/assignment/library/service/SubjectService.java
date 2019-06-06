package com.assignment.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.library.dao.SubjectJpaRepository;
import com.assignment.library.entities.Subject;

@Service("subjectService")
public class SubjectService implements ISubjectService {
	
	@Autowired
	private SubjectJpaRepository subjectJpaRepository;
	
	/* (non-Javadoc)
	 * @see com.assignment.library.service.ISubjectService#searchSubject(com.assignment.library.entities.Subject)
	 */
	@Override
	public List<Subject> searchSubject(Subject subject) {
		System.out.println("enter the keyword for subject subtitle to search");
		String subjectName = subject.getSubtitle();
		
		List<Subject> subjects = subjectJpaRepository.findBySubtitleLike("%"+subjectName+"%");
		System.out.println("search results for keyword: " + subjectName);
		if (!subjects.isEmpty()) {
			return subjects;
		} else {
			System.out.println("subjects not found with this title: " + subjectName + ". Please modify your search.");
		}
		return subjects;
	}

	/* (non-Javadoc)
	 * @see com.assignment.library.service.ISubjectService#addSubject(com.assignment.library.entities.Subject)
	 */
	@Override
	public String addSubject(Subject subject) {		
		subjectJpaRepository.saveAndFlush(subject);
		return "success";
	}

	/* (non-Javadoc)
	 * @see com.assignment.library.service.ISubjectService#deleteSubject(com.assignment.library.entities.Subject)
	 */
	@Override
	public String deleteSubject(Subject subjectr) {
		int subjectId = subjectr.getSubjectId();
		
		subjectJpaRepository.deleteById(subjectId);
		return "success";
	}

	@Override
	public List<Subject> searchSubjectByDurationEqual(Subject subject) {
		return subjectJpaRepository.findByDurationInHours(subject.getDurationInHours());
	}

}
