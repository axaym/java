import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProfileHighlightsComponent } from './profile-highlights/profile-highlights.component';
import { ProjectsComponent } from './projects/projects.component';
import { ExperienceComponent } from './experience/experience.component';
import { PersonalBiographyComponent } from './personal-biography/personal-biography.component';
import { ContactMeComponent } from './contact-me/contact-me.component';

const routes: Routes = [];

const appRoutes: Routes = [
  { path: 'profile-highlights', component: ProfileHighlightsComponent },
  { path: 'projects',      component: ProjectsComponent },
  { path: 'experience',      component: ExperienceComponent },
  { path: 'personal-biography',      component: PersonalBiographyComponent },
  { path: 'contact',      component: ContactMeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(
    appRoutes,
    { enableTracing: true } // <-- debugging purposes only
  )],
  exports: [RouterModule]
})




export class AppRoutingModule { }
