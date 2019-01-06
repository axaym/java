import { Component, OnInit, Output } from '@angular/core';
import { SubjectObject } from '../subject-object';
import { FormBuilder } from '@angular/forms';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-subject-search-duration',
  templateUrl: './subject-search-duration.component.html',
  styleUrls: ['./subject-search-duration.component.css']
})
export class SubjectSearchDurationComponent implements OnInit {

  subjectForm = this.fb.group({
    durationInHours: ['']
  });

  @Output() itemSearch = new EventEmitter<SubjectObject>();
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
  }

  searchItem() {
    let p:SubjectObject = new SubjectObject();
    p.durationInHours = this.subjectForm.value.durationInHours;
   
    this.itemSearch.emit(p);
  }
}


