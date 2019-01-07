import { Component, OnInit, Output, Input } from '@angular/core';
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
    bookId: [''],
    title: [''],
    price: [''],
    volume: [''],
    publishDate: [''],
    subjectId: ['']
  });

  @Input() selectedRow;
  @Output() itemAdded = new EventEmitter<BookObject>();
  @Output() itemUpdated = new EventEmitter<BookObject>();
  constructor(private fb: FormBuilder) { }

  ngOnInit() {
  }

  ngOnChanges(selectedRow) {
    if (selectedRow != null && selectedRow.currentValue != selectedRow.previousValue) {
      this.selectedRow = selectedRow.currentValue;
    }
  }

  addOrUpdateItem(event) {
    let p: BookObject = new BookObject();
    if (this.selectedRow != undefined && this.selectedRow.hasOwnProperty('bookId') && this.selectedRow.bookId != undefined) {
      p = this.selectedRow;
      if (this.bookForm.controls.title.touched) {
        p.title = this.bookForm.value.title;
      }
      if (this.bookForm.controls.price.touched) {
        p.price = this.bookForm.value.price;
      }
      if (this.bookForm.controls.volume.touched) {
        p.volume = this.bookForm.value.volume;
      }
      if (this.bookForm.controls.publishDate.touched) {
        p.publishDate = new Date(this.bookForm.value.publishDate).getTime();
      }
      if (this.bookForm.controls.subjectId.touched) {
        p.subjectId = this.bookForm.value.subjectId;
      }
      this.itemUpdated.emit(p);
    }
    else {
      //p.bookId = this.bookForm.value.bookId;
      p.title = this.bookForm.value.title;
      p.publishDate = new Date(this.bookForm.value.publishDate).getTime();
      p.price = this.bookForm.value.price;
      p.volume = this.bookForm.value.volume;
      p.subjectId = this.bookForm.value.subjectId;
      this.itemAdded.emit(p);
    }
  }
}


