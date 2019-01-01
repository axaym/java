import { Component, OnInit, Output } from '@angular/core';
import { SubjectObject } from '../subject-object';
import { FormBuilder } from '@angular/forms';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-subject-search',
  templateUrl: './subject-search.component.html',
  styleUrls: ['./subject-search.component.css']
})
export class SubjectSearchComponent implements OnInit {

  subjectForm = this.fb.group({
    subtitle: ['']
  });

  @Output() itemSearch = new EventEmitter<SubjectObject>();
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
  }

  searchItem() {
    let p:SubjectObject = new SubjectObject();
    p.subtitle = this.subjectForm.value.subtitle;
   
    this.itemSearch.emit(p);
  }
}


