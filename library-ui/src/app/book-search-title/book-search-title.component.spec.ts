import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookSearchTitleComponent } from './book-search-title.component';

describe('BookSearchTitleComponent', () => {
  let component: BookSearchTitleComponent;
  let fixture: ComponentFixture<BookSearchTitleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookSearchTitleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookSearchTitleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
