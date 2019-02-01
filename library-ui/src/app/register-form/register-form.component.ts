import { Component, OnInit, Output, Input } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { UsersObject } from '../users-object';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  registerForm = this.fb.group({
    username: [''],
    password: [''],
    role: ['']
  });

  constructor(private fb: FormBuilder,
      private userService: UserService) { }

  ngOnInit() {
  }

  register(event) {
    let p: UsersObject = new UsersObject();
    p.username = this.registerForm.value.username;
    p.password = this.registerForm.value.password;
    p.role = this.registerForm.value.role;

    this.userService.register({
      username: p.username,
      password: p.password, enabled: true,
      authoritieses: [
        {
          id: {
            username: p.username,
            authority: p.role
          }
        }
      ]
    }).
      then(
      res => this.showAlert("register status: " + res._body)
      );
  }

  showAlert(str) {
    alert(str);
  }
}


