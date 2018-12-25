import { Component, OnInit, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ProjectObject } from '../project-object';

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.css']
})
export class ProjectFormComponent implements OnInit {

  projectForm = this.fb.group({
    title: [''],
    duration: [''],
    location: [''],
    operatingSystems: [''],
    languages:[''],
    client:['']
  });

  @Output() itemAdded = new EventEmitter<ProjectObject>();
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
  }

  addItem() {
    let p:ProjectObject = new ProjectObject();
    p.id = 99;
    p.title = this.projectForm.value.title;
    p.location = this.projectForm.value.location;
    p.duration = this.projectForm.value.duration;
    p.operatingSystems = this.projectForm.value.operatingSystems;
    p.languages = this.projectForm.value.languages;
    p.client = this.projectForm.value.client;

    this.itemAdded.emit(p);
  }
}
