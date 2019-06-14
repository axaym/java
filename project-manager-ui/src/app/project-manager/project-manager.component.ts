import { Component, OnInit } from '@angular/core';
import { Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { UserService } from '../user.service';


@Component({
  selector: 'app-project-manager',
  templateUrl: './project-manager.component.html',
  styleUrls: ['./project-manager.component.css']
})
export class ProjectManagerComponent implements OnInit {
  
  @Input() userListData;
  
  constructor(public activeModal: NgbActiveModal, private userService:UserService) { }

  ngOnInit() {
      this.getUsers();
  }

  getUsers() {
    this.userService.getUsers().
      then(
      res => this.userListData = res
      );
  }

  selectItem(event) {
      this.activeModal.close(event);
  }

}
