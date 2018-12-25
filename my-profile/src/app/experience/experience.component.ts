import { Component, OnInit, Input } from '@angular/core';
import { ExperienceObject } from '../experience-object';
import { MatTableDataSource } from '@angular/material/table';


const ELEMENT_DATA: ExperienceObject[] = [{
  id: 1,
  company: "Cognizant",
  designation: "Manager - Projects",
  duration: "8"
}, {
  id: 2,
  company: "Persistent Systems Limited",
  designation: "Software Engineer",
  duration: "3"
}, {
  id: 3,
  company: "In2M Technologies Private Limited",
  designation: "Software Engineer",
  duration: "1.5"
}
];


@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  displayedColumns: string[] = ['id', 'company', 'designation', 'duration'];
  @Input() dataSource = new MatTableDataSource(ELEMENT_DATA);
  constructor() { }

  ngOnInit() {
  }

  getMaxId() {
    var id = 0;
    var res = Math.max.apply(Math, ELEMENT_DATA.map(function (o) {
      return o.id;
    }));
    id = res + 1;
    return id;
  }

  addExperienceItem(event) {
    ELEMENT_DATA.push({
      id: this.getMaxId(), company: event.company,
      designation: event.designation, duration: event.duration
    });
    this.dataSource = new MatTableDataSource(ELEMENT_DATA);
  }

  removeItem(row) {
    var index = ELEMENT_DATA.indexOf(row);
    if (index > -1) {
      ELEMENT_DATA.splice(index, 1);
      this.dataSource = new MatTableDataSource(ELEMENT_DATA);
    }
  }
}
