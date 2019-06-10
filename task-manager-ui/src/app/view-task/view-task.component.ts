import { Component, OnInit, Input, Inject } from '@angular/core';
import { TaskService } from '../task.service';
import { TaskObject } from '../task-object';

import { AddTaskComponent } from '../add-task/add-task.component';
import { DialogboxComponent } from '../dialogbox/dialogbox.component';
import * as moment from 'moment';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {

  @Input() taskListData;
  constructor(private taskService: TaskService, private dialog: MatDialog) { }


  ngOnInit() {
    this.getTasks();
  }

  getTasks() {
    this.taskService.getTasks().
    then(
      res => this.taskListData = res
    );
  }

  endTask(event) {
    let task:TaskObject = new TaskObject();
    Object.assign(task, event);
    task.status = 1;
    this.taskService.updateTask(task).
    then(
      res => this.showAlert("task add status: " + res._body)
    );
  }

  editTask(event) {
    const dialogRef = this.dialog.open(DialogboxComponent, {
      width: '700px',
      height: '600px',
      data: event
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }

  deleteTask(event) {
    this.taskService.deleteTask(event).
    then(
      res => this.showAlert("task delete status: " + res._body)
    );
  }

  showAlert(str) {
    alert(str);
  }
}
