package com.assignment.projectmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.projectmanager.dao.ParentTaskJpaRepository;
import com.assignment.projectmanager.entities.ParentTask;

@Service("parentTaskService")
public class ParentTaskService implements IParentTaskService {

	@Autowired
	private ParentTaskJpaRepository parentTaskJpaRepository;

	@Override
	public List<ParentTask> getParentTasks() {
		return parentTaskJpaRepository.findAll();
	}

	@Override
	public String addParentTask(ParentTask parentTask) {
		Integer parentId = parentTask.getParentId();
		if(parentTaskJpaRepository.existsById(parentId)) {
			parentTaskJpaRepository.saveAndFlush(parentTask);
			return "success";
		}
		return "fail";
	}

	@Override
	public String deleteParentTask(ParentTask parentTask) {
		parentTaskJpaRepository.delete(parentTask);
		return "success";
	}

	@Override
	public String updateParentTask(ParentTask parentTask) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
