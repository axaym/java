import { Component, OnInit, Input } from '@angular/core';
import { ProfileHighlightObject } from '../profile-highlight-object';
import { MatTableDataSource } from '@angular/material/table';


const ELEMENT_DATA: ProfileHighlightObject[] = [{
  id: 1,
  skill: "Programming Language",
  detail: "ReactJS, HTML5, Java script, Adobe Flex 4.5, Action script"
}, {
  id: 2,
  skill: "Programming Methodologies",
  detail: "Object oriented analysis and design"
}, {
  id: 3,
  skill: "Web Technologies",
  detail: "Java/J2EE"
}, {
  id: 4,
  skill: "ORM framework",
  detail: "Hibernate, IBatis"
}, {
  id: 5,
  skill: "UI Technology",
  detail: "ReactJS, Adobe Flex 4, Adobe AIR, Blaze DS"
}, {
  id: 6,
  skill: "UI Framework",
  detail: "Cairngorm, SWIZ, Mate, MVC"
}, {
  id: 7,
  skill: "Java Framework",
  detail: "SpringMVC"
}, {
  id: 8,
  skill: "Database",
  detail: "Oracle 10g"
}, {
  id: 9,
  skill: "Operating Systems",
  detail: "Windows (9X/ 2000/ NT/ XP / Vista)"
}, {
  id: 10,
  skill: "IDE",
  detail: "Eclipse, IBM RAD 6, Flex Builder 3, Flash Builder 4"
}, {
  id: 11,
  skill: "Server",
  detail: "Tomcat, Websphere6.1"
}, {
  id: 12,
  skill: "SCM Tool",
  detail: "GIT, SVN, Harvest"
}
];

@Component({
  selector: 'app-profile-highlights',
  templateUrl: './profile-highlights.component.html',
  styleUrls: ['./profile-highlights.component.css']
})


export class ProfileHighlightsComponent implements OnInit {

  displayedColumns: string[] = ['id', 'skill', 'detail'];
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
  
  addProfileItem(event) {
    ELEMENT_DATA.push({ id: this.getMaxId(), skill: event.skill, detail: event.detail });
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
