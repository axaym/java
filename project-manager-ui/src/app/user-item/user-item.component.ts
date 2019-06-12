import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { UserObject } from '../user-object';

@Component({
  selector: 'app-user-item',
  templateUrl: './user-item.component.html',
  styleUrls: ['./user-item.component.css']
})
export class UserItemComponent implements OnInit {

  @Input() selectedRow;
  @Output() editItem = new EventEmitter<UserObject>();
  @Output() deleteItem = new EventEmitter<UserObject>();

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
