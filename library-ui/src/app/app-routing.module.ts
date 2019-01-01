import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubjectComponent } from './subject/subject.component';
import { BookComponent } from './book/book.component';

const routes: Routes = [];

const appRoutes: Routes = [
  { path: 'book', component: BookComponent },
  { path: 'subject', component: SubjectComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(
    appRoutes,
    { enableTracing: true } // <-- debugging purposes only
  )],
  exports: [RouterModule]
})

export class AppRoutingModule { }
