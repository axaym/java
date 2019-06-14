import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-project-modal',
  templateUrl: './project-modal.component.html',
  styleUrls: ['./project-modal.component.css']
})

export class ProjectModalComponent implements OnInit {
  
  @Input() projectListData;
  
  constructor(public activeModal: NgbActiveModal, 
      private projectService:ProjectService) { }

  ngOnInit() {
      this.getProjects();
  }

  getProjects() {
    this.projectService.getProjects().
      then(
      res => this.projectListData = res
      );
  }

  selectItem(event) {
      this.activeModal.close(event);
  }

}

