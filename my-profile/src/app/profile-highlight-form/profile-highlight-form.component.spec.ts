import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileHighlightFormComponent } from './profile-highlight-form.component';

describe('ProfileHighlightFormComponent', () => {
  let component: ProfileHighlightFormComponent;
  let fixture: ComponentFixture<ProfileHighlightFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfileHighlightFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileHighlightFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
