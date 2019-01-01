import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { BookObject } from '../book-object';
import { BookService } from '../book.service';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {

  displayedColumns: string[] = ['bookId', 'title',
     'price', 'volume', "publishDate", "subjectId"];
  @Input() dataSource;
  constructor(private bookService: BookService) { }

  ngOnInit() {
  }

  searchBookItem(event) {
    let book = { title: event.title };
    this.bookService.searchBooks(book).
      then(
      res => this.dataSource = new MatTableDataSource(res)
      );
  }

  addBookItem(event) {
    this.bookService.addBook({
      title: event.title, publishDate: event.publishDate,
      price: event.price, subjectId: event.subjectId, volume: event.volume
    }).
      then(
      res => this.showAlert("add status: "+res.body)
      );
  }

  removeItem(row) {
    this.bookService.deleteBook({ bookId: row.bookId }).
      then(
      res => this.showAlert("delete status: "+res.body)
      );
  }

  showAlert(str) {
    alert(str);
  }
}
