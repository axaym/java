import { Component, OnInit, Input } from '@angular/core';
import { TaskService } from '../task.service';
import { TaskObject } from '../task-object';
import * as moment from 'moment';

@Component({
  selector: 'app-view-task',
  templateUrl: './view-task.component.html',
  styleUrls: ['./view-task.component.css']
})
export class ViewTaskComponent implements OnInit {

  @Input() taskListData;
  constructor(private taskService: TaskService) { }


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
    task.endDate = moment(new Date()).format("YYYY-MM-DD");
    this.taskService.updateTask(task).
    then(
      res => this.showAlert("task add status: " + res._body)
    );
  }

  editTask(event) {
    //open edit overlay
  }

  showAlert(str) {
    alert(str);
  }
}
