import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SubjectSearchDurationComponent } from './subject-search-duration.component';

describe('SubjectSearchDurationComponent', () => {
  let component: SubjectSearchDurationComponent;
  let fixture: ComponentFixture<SubjectSearchDurationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SubjectSearchDurationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SubjectSearchDurationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
