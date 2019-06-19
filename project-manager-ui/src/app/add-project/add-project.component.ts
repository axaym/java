import { Component, OnInit, Input } from '@angular/core';
import { ProjectService } from '../project.service';

@Component({
  selector: 'app-add-project',
  templateUrl: './add-project.component.html',
  styleUrls: ['./add-project.component.css']
})
export class AddProjectComponent implements OnInit {
  @Input() selectedManager: string;

  @Input() projectListData;
  @Input() selectedFormData;
  @Input() submitLabel;

  constructor(private projectService: ProjectService) { }

  ngOnInit() {
    this.getProjects();
  }

  sortDate(prop) {
    this.projectListData = this.projectListData.sort(function(a, b){      
      let dateA = new Date(a[prop]);
      let dateB = new Date(b[prop]);      
     return dateB.getTime() - dateA.getTime();
    });
  }

  sortNumber(prop) {
    this.projectListData = this.projectListData.sort(function(a, b){      
      var x = a[prop];
      var y = b[prop];
      if (x < y) {return -1;}
      if (x > y) {return 1;}
      return 0;
    });
  }

  getProjects() {
    this.projectService.getProjects().
      then(
      res => this.projectListData = res
      );
  }

  editProject(event) {
    this.selectedFormData = event;
    this.selectedManager = event.user.firstName+ ' ' + event.user.lastName;
    this.submitLabel = "Update";
  }

  deleteProject(event) {
    this.projectService.deleteProject(event).
    then(
      res => this.showAlertAndRefreshList(res._body, "delete")
    );
  }

  editProjectServiceCall(event) {
    this.projectService.updateProject(event).
    then(
      res => this.showAlertAndRefreshList(res._body, "update")
    );
  }

  addProject(event) {
    this.projectService.addProject(event).
    then(
      res => this.showAlertAndRefreshList(res._body, "add")
    );
  }

  showAlert(str) {
    alert(str);
  }

  showAlertAndRefreshList(status, type) {
    this.showAlert("project "+type+" status: " + status)
    if(status === "success") {
      this.getProjects();
    }
  }
}
