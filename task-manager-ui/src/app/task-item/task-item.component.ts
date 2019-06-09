import { Component, OnInit, Input, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { TaskObject } from '../task-object';

@Component({
  selector: 'app-task-item',
  templateUrl: './task-item.component.html',
  styleUrls: ['./task-item.component.css']
})
export class TaskItemComponent implements OnInit {
  
  @Input() selectedRow;
  @Output() endTaskItem = new EventEmitter<TaskObject>();
  @Output() editItem = new EventEmitter<TaskObject>();
  
  constructor() { }

  ngOnInit() {
  }

  endTask(event) {
    this.endTaskItem.emit(this.selectedRow);
  }

  edit(event) {
    this.editItem.emit(this.selectedRow);
  }

}
