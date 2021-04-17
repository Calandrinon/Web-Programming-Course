import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navigation-bar',
  templateUrl: './navigation-bar.component.html',
  styleUrls: ['./navigation-bar.component.css']
})
export class NavigationBarComponent implements OnInit {
  tabs: string[] = ["Home", "Users and roles", "Search users by name", "Add a user", "Remove a user", "Update a user"];

  constructor() { }

  ngOnInit(): void {
  }

}
