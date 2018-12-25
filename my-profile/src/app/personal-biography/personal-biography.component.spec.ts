import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalBiographyComponent } from './personal-biography.component';

describe('PersonalBiographyComponent', () => {
  let component: PersonalBiographyComponent;
  let fixture: ComponentFixture<PersonalBiographyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PersonalBiographyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalBiographyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
