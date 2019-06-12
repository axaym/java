import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../user.service';
@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  @Input() userListData;
  @Input() selectedFormData;
  @Input() submitLabel;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.getUsers();
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
