package com.assignment.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assignment.taskmanager.dao.ParentTaskJpaRepository;
import com.assignment.taskmanager.entities.ParentTask;

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
			return "Task updated successfully";
		}
		return "task does not exist to update";
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
