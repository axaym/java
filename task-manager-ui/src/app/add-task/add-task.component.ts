import { Component, OnInit, Output, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { TaskObject } from '../task-object';
import { TaskService } from '../task.service';
import { ParentTaskService } from '../parent-task.service';
import * as moment from 'moment';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css']
})
export class AddTaskComponent implements OnInit {

  @Input() parentTaskListData;

  taskForm = this.fb.group({
    taskId: [''],
    task: [''],
    priority: [''],
    status: [''],
    startDate: [''],
    endDate: [''],
    parentId: ['']
  });

  constructor(private fb: FormBuilder, private taskService: TaskService, private parentTaskService: ParentTaskService) { }

  ngOnInit() {
    this.getParentTasks();
  }

  getParentTasks() {
    this.parentTaskService.getParentTasks().
    then(
      res => this.parentTaskListData = res
    );

  }

  addTask(event) {
    let p: TaskObject = new TaskObject();

    p.task = this.taskForm.value.task;
    p.parentId = this.taskForm.value.parentId;
    p.priority = this.taskForm.value.priority;
    p.startDate = moment(this.taskForm.value.startDate).format('YYYY-MM-DD');
    p.endDate = moment(this.taskForm.value.endDate).format('YYYY-MM-DD');

    this.taskService.addTask(p).
      then(
        res => this.showAlert("task add status: " + res._body)
      );
  }

  showAlert(str) {
    alert(str);
  }

}
