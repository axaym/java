import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  @Input('data') data : Object;

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  onGetUsersClick() {
    this.userService.getUsers().then(res=>this.data=JSON.stringify(res, undefined, 2));
  }

  onGetUserClick() {
    this.userService.getUser().then(res=>this.data=JSON.stringify(res, undefined, 2));
  }

  onPostUserClick() {
    this.userService.postUser().then(res=>this.data=JSON.stringify(res, undefined, 2));
  }

  onUpdateUserClick() {
    this.userService.updateUser().then(res=>this.data=JSON.stringify(res, undefined, 2));
  }

  onDeleteUserClick() {
    this.userService.deleteUser().then(res=>this.data=JSON.stringify(res, undefined, 2));
  }

}
