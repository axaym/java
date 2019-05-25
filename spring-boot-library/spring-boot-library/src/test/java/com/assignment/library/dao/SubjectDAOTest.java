
package com.assignment.library.dao;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.assignment.library.entities.Subject;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubjectDAOTest {

	@Autowired
	private SubjectJpaRepository subjectJpaRepository;

	@Test
	public void testSaveAndFindSubject() throws Exception {
		Subject subject1 = getSubject(1, 12, "Electronics");
		subjectJpaRepository.saveAndFlush(subject1);

		List<Subject> subjects = subjectJpaRepository.findBySubtitleLike(subject1.getSubtitle());

		Assertions.assertEquals(subject1.getSubtitle(), subjects.get(0).getSubtitle());
		Assertions.assertEquals(subject1.getDurationInHours(), subjects.get(0).getDurationInHours());
		
	}
	
	@Test
	public void testDeleteAndFindSubject() throws Exception {
		Subject subject1 = getSubject(1, 12, "Computer Science");
		subjectJpaRepository.saveAndFlush(subject1);
		List<Subject> subjects = subjectJpaRepository.findBySubtitleLike(subject1.getSubtitle());
		subjectJpaRepository.deleteById(subjects.get(0).getSubjectId());

		List<Subject> subjectsAfterDeletion = subjectJpaRepository.findBySubtitleLike(subject1.getSubtitle());
		Assertions.assertEquals(0, subjectsAfterDeletion.size());
	}

	private Subject getSubject(int subjectId, int duration, String subTitle) {
		Subject subject1 = new Subject();
		subject1.setSubjectId(subjectId);
		subject1.setDurationInHours(duration);
		subject1.setSubtitle(subTitle);
		return subject1;
	}
}
