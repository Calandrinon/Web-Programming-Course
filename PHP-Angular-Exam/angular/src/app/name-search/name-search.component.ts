import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { UserListComponent } from '../user-list/user-list.component';

@Component({
  selector: 'app-name-search',
  templateUrl: './name-search.component.html',
  styleUrls: ['./name-search.component.css']
})
export class NameSearchComponent implements OnInit {
  nameFilterText?: string;
  @ViewChild(UserListComponent) child?:UserListComponent;
  filterHistory: string[] = [];
  newFilterHistoryTerm?: string;

  constructor() { }

  ngOnInit(): void {
  }

  searchButtonClick(nameFilterText: string): void{
    console.log("Name filter text: ", nameFilterText);
    console.log("Result: ", this.child?.getUsersByName(nameFilterText));
    this.filterHistory.push(nameFilterText);
    console.log(this.filterHistory);

    if (this.filterHistory.length >= 2) {
      this.newFilterHistoryTerm = this.filterHistory[this.filterHistory.length-2];
    } else {
      this.newFilterHistoryTerm = "None"; 
    }
  }

}
