import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../user.service';
import { ViewChild } from '@angular/core';
import { UserFormComponent } from 'src/app/user-form/user-form.component';
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  @Input() userListData;
  @Input() selectedFormData;
  @Input() submitLabel;
  @ViewChild('userForm') userForm : UserFormComponent;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getUsers();
  }

  sortList(prop) {
    this.userListData = this.userListData.sort(function(a, b){
      var x = a[prop].toLowerCase();
      var y = b[prop].toLowerCase();
      if (x < y) {return -1;}
      if (x > y) {return 1;}
      return 0;
    });
  }

  getUsers() {
    this.userService.getUsers().
      then(
      res => this.userListData = res
      );
  }

  editUser(event) {
    this.selectedFormData = event;
    this.submitLabel = "Update";
    this.userForm.callUserFormFunc(event);
  }

  deleteUser(event) {
    this.userService.deleteUser(event).
    then(
      res => this.showAlertAndRefreshList(res._body, "delete")
    );
  }

  editUserServiceCall(event) {
    this.userService.updateUser(event).
    then(
      res => this.showAlertAndRefreshList(res._body, "update")
    );
  }

  addUser(event) {
    this.userService.addUser(event).
    then(
      res => this.showAlertAndRefreshList(res._body, "add")
    );
  }

  showAlert(str) {
    alert(str);
  }

  showAlertAndRefreshList(status, type) {
    this.showAlert("user "+type+" status: " + status)
    if(status === "success") {
      this.getUsers();
    }
  }
}
