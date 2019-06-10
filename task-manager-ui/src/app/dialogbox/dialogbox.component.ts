import { Component, OnInit, Inject, Input, SimpleChanges } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder } from '@angular/forms';
import { TaskObject } from '../task-object';
import { TaskService } from '../task.service';
import { ParentTaskService } from '../parent-task.service';
import * as moment from 'moment';

@Component({
  selector: 'app-dialogbox',
  templateUrl: './dialogbox.component.html',
  styleUrls: ['./dialogbox.component.css']
})
export class DialogboxComponent implements OnInit {

  @Input() parentTaskListData = [{"parentId":1,"parentTask":"Parent task1"},{"parentId":2,"parentTask":"Parent task22"},{"parentId":7,"parentTask":"My first task1"}];
  @Input() selectedParentTask;

  taskForm = this.fb.group({
    taskId: [''],
    task: [''],
    priority: [''],
    status:[''],
    startDate: [''],
    endDate: [''],
    parentId: [''],
    parentTask: {}
  });

  constructor(
    public dialogRef: MatDialogRef<DialogboxComponent>,
    @Inject(MAT_DIALOG_DATA) public data: TaskObject,
    private fb: FormBuilder, private taskService: TaskService, private parentTaskService: ParentTaskService) { }

  ngOnInit() {
    //this.getParentTasks();
  }

  getParentTasks() {
    this.parentTaskService.getParentTasks().
      then(
      res => this.parentTaskListData = res
      );      
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  updateTask(event) {
    let p: TaskObject = new TaskObject();
    p = this.data;
    if (this.taskForm.controls.task.touched) {
      p.task = this.taskForm.value.task;
    }
    if (this.taskForm.controls.parentId.touched) {
      p.parentId = this.taskForm.value.parentId;
    }
    if (this.taskForm.controls.priority.touched) {
      p.priority = this.taskForm.value.priority;
    }
    if (this.taskForm.controls.startDate.touched) {
      p.startDate = moment(this.taskForm.value.startDate).format('YYYY-MM-DD');
    }    
    if (this.taskForm.controls.endDate.touched) {
      p.endDate = moment(this.taskForm.value.endDate).format('YYYY-MM-DD');
    }       

   /* this.taskService.updateTask(p).
      then(
        res => this.showAlert("task update status: " + res._body)
      );*/
  }

  showAlert(str) {
    alert(str);
  }
}
