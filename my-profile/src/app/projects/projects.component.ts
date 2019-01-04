import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  @ViewChild('projects') projects: ElementRef;
  @ViewChild('table2') table2: ElementRef;
  @ViewChild('project1') project1: ElementRef;

  constructor() { }

  ngOnInit() {
  }

  addProject(event) {
    this.addTable(this.getMaxId());

    this.addRow(0, "Project Title", event.title);
    this.addRow(1, "Duration", event.duration);
    this.addRow(2, "Location", event.location);
    this.addRow(3, "Operating Systems", event.operatingSystems);
    this.addRow(4, "Programming languages, Environment", event.languages);
    this.addRow(5, "Client", event.client);
  }

  getMaxId() {
    var id = 0;
    var size = this.projects.nativeElement.children.length;
    id = size + 1;
    return id;
  }
  addTable(i) {
    var markup = "<div #project" + i + "><h2>PROJECT PROFILE</h2><form><div class='form-group col-sm-12 btn btn-primary'><input type='submit' class='form-control' value='Remove' (click)='removeProject($event, 'project" + i + "')'></div>  </form><table #table2 _ngcontent-c3 class='table table-bordered'></div>";
    //this.projects.nativeElement.prepend(markup);
    this.projects.nativeElement.insertAdjacentHTML('afterbegin', markup);
  }

  addRow(rowIndex, label, value) {
    var markup = "<tr>  <td>" + label + "</td> <td>" + value + "</td> </tr>";
    //this.table2.nativeElement.append(markup);
    this.table2.nativeElement.insertAdjacentHTML('beforeend', markup);
  }

  removeProject(event, projectId) {
    event.preventDefault();
    projectId.remove();
  }
}
