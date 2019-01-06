import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { SubjectObject } from '../subject-object';
import { SubjectService } from '../subject.service';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {

  displayedColumns: string[] = ['i', 'subjectId', 'subtitle',
    'durationInHours'];
  @Input() dataSource;
  constructor(private subjectService: SubjectService) { }

  ngOnInit() {
  }

  searchSubjectItem(event) {
    let subject = { subtitle: event.subtitle };
    this.subjectService.searchSubjects(subject).
      then(
      res => this.dataSource = new MatTableDataSource(res)
      );
  }

  searchSubjectByDuration(event) {
    let subject = { durationInHours: event.durationInHours };
    this.subjectService.searchSubjectByDuration(subject).
      then(
      res => this.dataSource = new MatTableDataSource(res)
      );
  }
  
  addSubjectItem(event) {
    this.subjectService.addSubject({
      subtitle: event.subtitle, durationInHours: event.durationInHours
    }).
      then(
      res => this.showAlert("add status: " + res._body)
      );
  }

  removeItem(row) {
    this.subjectService.deleteSubject({ subjectId: row.subjectId }).
      then(
      res => this.showAlert("delete status: " + res._body)
      );
  }

  showAlert(str) {
    alert(str);
  }
}
