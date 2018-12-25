import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavigationbarComponent } from './navigationbar/navigationbar.component';
import { ProfileHighlightsComponent } from './profile-highlights/profile-highlights.component';
import { ProjectsComponent } from './projects/projects.component';
import { ExperienceComponent } from './experience/experience.component';
import { PersonalBiographyComponent } from './personal-biography/personal-biography.component';
import { ContactMeComponent } from './contact-me/contact-me.component';
import { MatTableModule } from '@angular/material/table';
import { ProfileHighlightFormComponent } from './profile-highlight-form/profile-highlight-form.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ExperienceFormComponent } from './experience-form/experience-form.component';
import { ProjectFormComponent } from './project-form/project-form.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavigationbarComponent,
    ProfileHighlightsComponent,
    ProjectsComponent,
    ExperienceComponent,
    PersonalBiographyComponent,
    ContactMeComponent,
    ProfileHighlightFormComponent,
    ExperienceFormComponent,
    ProjectFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatTableModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
