package com.assignment.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.assignment.library.entities.Subject;
import com.assignment.library.service.ISubjectService;

@CrossOrigin(origins="http://localhost:4200")
@Controller("subjectController")
@RequestMapping(value = "/subject")
public class SubjectController {

	@Autowired
	private ISubjectService subjectService;
		
	/**
	 * @param subject
	 * @return
	 */
	@PostMapping("/search")
    public @ResponseBody List<Subject> searchSubjects(@RequestBody Subject subject) {
        return subjectService.searchSubject(subject);
    }
	
	/**
	 * @param subject
	 * @return
	 */
	@PostMapping("/add")
    public @ResponseBody String addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject);
    }
	
	/**
	 * @param subject
	 * @return
	 */
	@PostMapping("/delete")
    public @ResponseBody String deleteSubject(@RequestBody Subject subject) {
        return subjectService.deleteSubject(subject);
    }
}
