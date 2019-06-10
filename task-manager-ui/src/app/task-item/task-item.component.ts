import { Component, OnInit, Input, Output, SimpleChanges } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { TaskObject } from '../task-object';
import * as moment from 'moment';

@Component({
  selector: 'app-task-item',
  templateUrl: './task-item.component.html',
  styleUrls: ['./task-item.component.css']
})
export class TaskItemComponent implements OnInit {

  @Input() selectedRow;
  @Input() selectedStartDate;
  @Input() selectedEndDate;
  @Output() endTaskItem = new EventEmitter<TaskObject>();
  @Output() editItem = new EventEmitter<TaskObject>();
  @Output() deleteItem = new EventEmitter<TaskObject>();

  constructor() { }

  ngOnInit() {
  }

  ngOnChanges(changes: SimpleChanges) {

    for (let propName in changes) {
      let change = changes[propName];
      if (propName === "selectedRow") {
        this.selectedStartDate = moment(change.currentValue.startDate).format("MM/DD/YYYY");
        this.selectedEndDate = moment(change.currentValue.endDate).format("MM/DD/YYYY");
      }
    }

  }

  endTask(event) {
    this.endTaskItem.emit(this.selectedRow);
  }

  edit(event) {
    this.editItem.emit(this.selectedRow);
  }

  delete(event) {
    this.deleteItem.emit(this.selectedRow);
  }

}
