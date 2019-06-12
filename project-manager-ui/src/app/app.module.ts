import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { HomeComponent } from './home/home.component';
import { AddProjectComponent } from './add-project/add-project.component';
import { ProjectFormComponent } from './project-form/project-form.component';
import { ProjectItemComponent } from './project-item/project-item.component';
import { AddTaskComponent } from './add-task/add-task.component';
import { AddUserComponent } from './add-user/add-user.component';
import { UserFormComponent } from './user-form/user-form.component';
import { UserItemComponent } from './user-item/user-item.component';
import { ViewTaskComponent } from './view-task/view-task.component';
import { TaskItemComponent } from './task-item/task-item.component';

import {TaskService} from './task.service';
import {ParentTaskService} from './parent-task.service';
import {UserService} from './user.service';
import {ProjectService} from './project.service';

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    HomeComponent,
    AddProjectComponent,
    ProjectFormComponent,
    ProjectItemComponent,
    AddTaskComponent,
    AddUserComponent,
    UserFormComponent,
    UserItemComponent,
    ViewTaskComponent,
    TaskItemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule
  ],
  providers: [TaskService, ParentTaskService, UserService, ProjectService],
  bootstrap: [AppComponent]
})
export class AppModule { }
