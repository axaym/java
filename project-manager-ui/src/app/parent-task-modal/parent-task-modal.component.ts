import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ParentTaskService } from '../parent-task.service';


@Component({
  selector: 'app-parent-task-modal',
  templateUrl: './parent-task-modal.component.html',
  styleUrls: ['./parent-task-modal.component.css']
})

export class ParentTaskModalComponent implements OnInit {
  
  @Input() parentTaskListData;
  
  constructor(public activeModal: NgbActiveModal, 
      private parentTaskService:ParentTaskService) { }

  ngOnInit() {
      this.getParentTasks();
  }

  getParentTasks() {
    this.parentTaskService.getParentTasks().
      then(
      res => this.parentTaskListData = res
      );
  }

  selectItem(event) {
      this.activeModal.close(event);
  }

}


