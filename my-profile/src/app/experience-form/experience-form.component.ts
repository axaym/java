import { Component, OnInit, Output } from '@angular/core';
import { ExperienceObject } from '../experience-object';
import { FormBuilder } from '@angular/forms';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-experience-form',
  templateUrl: './experience-form.component.html',
  styleUrls: ['./experience-form.component.css']
})
export class ExperienceFormComponent implements OnInit {

  experienceForm = this.fb.group({
    company: [''],
    designation: [''],
    duration: ['']
  });

  @Output() itemAdded = new EventEmitter<ExperienceObject>();
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
  }

  addItem() {
    let p:ExperienceObject = new ExperienceObject();
    p.id = 99;
    p.company = this.experienceForm.value.company;
    p.designation = this.experienceForm.value.designation;
    p.duration = this.experienceForm.value.duration;

    this.itemAdded.emit(p);
  }
}

