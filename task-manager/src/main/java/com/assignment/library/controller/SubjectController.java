package com.assignment.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.library.entities.Subject;
import com.assignment.library.service.ISubjectService;

@CrossOrigin(origins="http://localhost:4200")
@RestController("subjectController")
public class SubjectController {

	@Autowired
	private ISubjectService subjectService;
		
	/**
	 * @param subject
	 * @return
	 */
	@PostMapping("/subject/search")
    public @ResponseBody List<Subject> searchSubjects(@RequestBody Subject subject) {
        return subjectService.searchSubject(subject);
    }
	
	/**
	 * @param subject
	 * @return
	 */
	@PostMapping("/subject/search/duration")
    public @ResponseBody List<Subject> searchSubjectsByDuration(@RequestBody Subject subject) {
        return subjectService.searchSubjectByDurationEqual(subject);
    }
	
	/**
	 * @param subject
	 * @return
	 */
	@PostMapping("/subject/add")
    public @ResponseBody String addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }
	
	/**
	 * @param subject
	 * @return
	 */
	@PostMapping("/subject/delete")
    public @ResponseBody String deleteSubject(@RequestBody Subject subject) {
        return subjectService.deleteSubject(subject);
    }
}
