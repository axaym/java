import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';

import { FormBuilder, Validators } from '@angular/forms';
import { UserObject } from '../user-object';

import { UserService } from '../user.service';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  @Input() submitLabel;
  @Input() selectedFormData;
  @Output() itemAdded = new EventEmitter<UserObject>();
  @Output() itemUpdated = new EventEmitter<UserObject>();
  @Input() submitted = false;
  
  userForm = this.fb.group({
    userId: [''],
    employeeId: ['', Validators.required],
    firstName: ['', Validators.required],
    lastName: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private userService: UserService) { }

  ngOnInit() {
    this.submitLabel = "Add";
  }

  get f() {
     return this.userForm.controls; 
  }

  addOrUpdateItem(event) {
    this.submitted = true;
    
    if (this.userForm.invalid) {
        return;
    }
    let p: UserObject = new UserObject();
    if (this.selectedFormData != undefined && this.selectedFormData.hasOwnProperty('userId') && this.selectedFormData.userId != undefined) {
      p = this.selectedFormData;
      if (this.userForm.controls.employeeId.touched) {
        p.employeeId = this.userForm.value.employeeId;
      }
      if (this.userForm.controls.firstName.touched) {
        p.firstName = this.userForm.value.firstName;
      }
      if (this.userForm.controls.lastName.touched) {
        p.lastName = this.userForm.value.lastName;
      }
      this.itemUpdated.emit(p);
    }
    else {
      p.employeeId = this.userForm.value.employeeId;
      p.firstName = this.userForm.value.firstName;
      p.lastName = this.userForm.value.lastName;
      this.itemAdded.emit(p);
    }
    this.clearForm();
  }

  clearForm() {
    this.userForm.reset();
    this.submitLabel = "Add";
    this.submitted = false;
    this.userForm.controls.firstName.setValue(undefined);
    this.userForm.controls.lastName.setValue(undefined);
    this.userForm.controls.employeeId.setValue(undefined);
  
  }

  callUserFormFunc(selectedObj) {
    this.submitted = false;
    this.submitLabel = "Update";
    this.userForm.controls.firstName.setValue(selectedObj.firstName);
    this.userForm.controls.lastName.setValue(selectedObj.lastName);
    this.userForm.controls.employeeId.setValue(selectedObj.employeeId);
  }

  showAlert(str) {
    alert(str);
  }

}
