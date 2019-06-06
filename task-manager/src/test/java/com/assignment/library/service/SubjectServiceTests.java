package com.assignment.library.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.assignment.library.dao.SubjectJpaRepository;
import com.assignment.library.entities.Subject;

@ContextConfiguration(classes = { SubjectService.class })
@WebMvcTest
@AutoConfigureMockMvc
public class SubjectServiceTests {

	@Autowired
	private ISubjectService subjectService;

	@MockBean
	private SubjectJpaRepository subjectJpaRepository;

	@Test
	public void testSearchSubjects() throws IOException {
		Subject subject1 = getSubject(1, 12, "Computer Science");
		Subject subject2 = getSubject(1, 12, "Electronics");		

		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject1);
		subjects.add(subject2);

		when(subjectJpaRepository.findBySubtitleLike("%"+subject2.getSubtitle()+"%")).thenReturn(subjects);
		List<Subject> subjectsFromService = subjectService.searchSubject(subject2);
		Assertions.assertNotNull(subjectsFromService);
	}
	
	@Test
	public void testSearchSubjectsByDuration() throws IOException {
		Subject subject1 = getSubject(1, 12, "Computer Science");

		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject1);

		when(subjectJpaRepository.findByDurationInHours(subject1.getDurationInHours())).thenReturn(subjects);
		List<Subject> subjectsFromService = subjectService.searchSubjectByDurationEqual(subject1);
		Assertions.assertNotNull(subjectsFromService);
		Assertions.assertEquals(subjects.size(), subjectsFromService.size());
	}
	
	@Test
	public void testAddSubject() throws IOException {
		Subject subject1 = getSubject(1, 12, "Computer Science");

		when(subjectJpaRepository.saveAndFlush(subject1)).thenReturn(subject1);
		String status = subjectService.addSubject(subject1);
		Assertions.assertEquals("success", status);
	}
	
	@Test
	public void testDeleteSubject() throws IOException {
		Subject subject1 = getSubject(1, 12, "Computer Science");

		doNothing().when(subjectJpaRepository).deleteById(subject1.getSubjectId());
		String status = subjectService.deleteSubject(subject1);
		Assertions.assertEquals("success", status);
	}

	private Subject getSubject(int subjectId, int duration, String subTitle) {
		Subject subject1 = new Subject();
		subject1.setSubjectId(subjectId);
		subject1.setDurationInHours(duration);
		subject1.setSubtitle(subTitle);
		return subject1;
	}
}
