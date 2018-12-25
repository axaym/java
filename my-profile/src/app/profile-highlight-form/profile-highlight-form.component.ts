import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { EventEmitter } from '@angular/core';
import { ProfileHighlightObject } from '../profile-highlight-object';

@Component({
  selector: 'profile-highlight-form',
  templateUrl: './profile-highlight-form.component.html',
  styleUrls: ['./profile-highlight-form.component.css']
})
export class ProfileHighlightFormComponent implements OnInit {

  profileForm = this.fb.group({
    skill: [''],
    detail: ['']
  });

  @Output() itemAdded = new EventEmitter<ProfileHighlightObject>();
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
  }

  addItem() {
    let p:ProfileHighlightObject = new ProfileHighlightObject();
    p.id = 99;
    p.skill = this.profileForm.value.skill;
    p.detail = this.profileForm.value.detail;
    this.itemAdded.emit(p);
  }
}
