import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { UserListComponent } from '../user-list/user-list.component';

@Component({
  selector: 'app-role-search',
  templateUrl: './role-search.component.html',
  styleUrls: ['./role-search.component.css']
})
export class RoleSearchComponent implements OnInit {
  roleFilterText?: string;
  @ViewChild(UserListComponent) child?:UserListComponent;
  filterHistory: string[] = [];
  newFilterHistoryTerm?: string;

  constructor() { }

  ngOnInit(): void {
  }

  searchButtonClick(roleFilterText: string): void{
    console.log("Role filter text: ", roleFilterText);
    console.log("Result: ", this.child?.getUsersByRole(roleFilterText));
    this.filterHistory.push(roleFilterText);
    console.log(this.filterHistory);

    if (this.filterHistory.length >= 2) {
      this.newFilterHistoryTerm = this.filterHistory[this.filterHistory.length-2];
    } else {
      this.newFilterHistoryTerm = "None"; 
    }
  }

}
