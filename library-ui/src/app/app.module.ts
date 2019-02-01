import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { BookFormComponent } from './book-form/book-form.component';
import { SubjectFormComponent } from './subject-form/subject-form.component';
import { BookComponent } from './book/book.component';
import { SubjectComponent } from './subject/subject.component';
import { MatTableModule } from '@angular/material/table';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BookService } from './book.service';
import { SubjectService } from './subject.service';
import { UserService } from './user.service';
import { BookSearchComponent } from './book-search/book-search.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { SubjectSearchComponent } from './subject-search/subject-search.component';
import { BookSearchTitleComponent } from './book-search-title/book-search-title.component';
import { SubjectSearchDurationComponent } from './subject-search-duration/subject-search-duration.component';
import { RegisterFormComponent } from './register-form/register-form.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    BookFormComponent,
    SubjectFormComponent,
    BookComponent,
    SubjectComponent,
    BookSearchComponent,
    NavigationBarComponent,
    SubjectSearchComponent,
    BookSearchTitleComponent,
    SubjectSearchDurationComponent,
    RegisterFormComponent,    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTableModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule
  ],
  providers: [BookService, SubjectService, UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
