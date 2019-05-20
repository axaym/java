package com.assignment.library.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.assignment.library.entities.Subject;
import com.assignment.library.service.ISubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { SubjectController.class })
@WebMvcTest
@AutoConfigureMockMvc
public class SubjectControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ISubjectService subjectService;

	@Test
	public void testSearchSubjects() throws Exception {
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

		when(subjectService.searchSubject(subject1)).thenReturn(subjects);
		mockMvc.perform(MockMvcRequestBuilders.post("/subject/search").with(user("axay")).with(csrf())
				.contentType(MediaType.APPLICATION_JSON).content(asJsonString(subject1))
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
