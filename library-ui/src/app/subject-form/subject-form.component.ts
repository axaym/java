import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { EventEmitter } from '@angular/core';
import { SubjectObject } from '../subject-object';

@Component({
  selector: 'app-subject-form',
  templateUrl: './subject-form.component.html',
  styleUrls: ['./subject-form.component.css']
})
export class SubjectFormComponent implements OnInit {
  subjectForm = this.fb.group({
    subtitle: [''],
    durationInHours: ['']
  });

  @Output() itemAdded = new EventEmitter<SubjectObject>();
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
  }

  addItem() {
    let p:SubjectObject = new SubjectObject();
    p.subtitle = this.subjectForm.value.subtitle;
    p.durationInHours = this.subjectForm.value.durationInHours;
    
    this.itemAdded.emit(p);
  }
}


