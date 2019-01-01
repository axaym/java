import { Component, OnInit, Output } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { EventEmitter } from '@angular/core';
import { BookObject } from '../book-object';

@Component({
  selector: 'app-book-form',
  templateUrl: './book-form.component.html',
  styleUrls: ['./book-form.component.css']
})
export class BookFormComponent implements OnInit {

  bookForm = this.fb.group({
    title: [''],
    price: [''],
    volume: [''],
    publishDate: [''],
    subjectId: ['']
  });

  @Output() itemAdded = new EventEmitter<BookObject>();
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
  }

  addItem() {
    let p:BookObject = new BookObject();
    p.title = this.bookForm.value.title;
    p.publishDate = this.bookForm.value.publishDate;
    p.price = this.bookForm.value.price;
    p.volume = this.bookForm.value.volume;
    p.subjectId = this.bookForm.value.subjectId;

    this.itemAdded.emit(p);
  }
}


