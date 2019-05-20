package com.assignment.library.service;

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
		Subject subject1 = new Subject();
		subject1.setSubjectId(1);
		subject1.setDurationInHours(12);
		subject1.setSubtitle("PQR");

		Subject subject2 = new Subject();
		subject2.setSubjectId(2);
		subject2.setDurationInHours(22);
		subject2.setSubtitle("XYZ");

		List<Subject> subjects = new ArrayList<>();
		subjects.add(subject1);
		subjects.add(subject2);

		when(subjectJpaRepository.findBySubtitleLike("%"+subject2.getSubtitle()+"%")).thenReturn(subjects);
		List<Subject> subjectsFromService = subjectService.searchSubject(subject2);
		Assertions.assertNotNull(subjectsFromService);

	}
}
