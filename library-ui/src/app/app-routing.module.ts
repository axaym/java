import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubjectComponent } from './subject/subject.component';
import { BookComponent } from './book/book.component';
import { RegisterFormComponent } from './register-form/register-form.component';


const routes: Routes = [];

const appRoutes: Routes = [
  { path: 'bookpage', component: BookComponent },
  { path: 'subjectpage', component: SubjectComponent },
  { path: 'registerpage', component: RegisterFormComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(
    appRoutes,
    { enableTracing: true } // <-- debugging purposes only
  )],
  exports: [RouterModule]
})

export class AppRoutingModule { }
