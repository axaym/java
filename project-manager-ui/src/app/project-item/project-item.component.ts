import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { ProjectObject } from '../project-object';

@Component({
  selector: 'app-project-item',
  templateUrl: './project-item.component.html',
  styleUrls: ['./project-item.component.css']
})
export class ProjectItemComponent implements OnInit {

  @Input() selectedRow;
  @Output() editItem = new EventEmitter<ProjectObject>();
  @Output() deleteItem = new EventEmitter<ProjectObject>();

  constructor() { }

  ngOnInit() {
  }

  edit(event) {
    this.editItem.emit(this.selectedRow);
  }

  delete(event) {
    this.deleteItem.emit(this.selectedRow);
  }
}
