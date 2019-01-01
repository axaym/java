import { Component, OnInit, Output } from '@angular/core';
import { BookObject } from '../book-object';
import { FormBuilder } from '@angular/forms';
import { EventEmitter } from '@angular/core';

@Component({
  selector: 'app-book-search',
  templateUrl: './book-search.component.html',
  styleUrls: ['./book-search.component.css']
})
export class BookSearchComponent implements OnInit {

  bookForm = this.fb.group({
    title: ['']
  });

  @Output() itemSearch = new EventEmitter<BookObject>();
  constructor(private fb:FormBuilder) { }

  ngOnInit() {
  }

  searchItem() {
    let p:BookObject = new BookObject();
    p.title = this.bookForm.value.title;
   
    this.itemSearch.emit(p);
  }
}


