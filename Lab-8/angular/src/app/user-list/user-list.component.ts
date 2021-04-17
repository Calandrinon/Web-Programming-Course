import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user.model';
import { RoleService } from '../role-search/service/role.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  users: User[] = [];

  constructor(private roleService: RoleService) { }

  ngOnInit(): void {
    this.getUsersByRole("Dev");
  }

  getUsersByRole(role: string): void {
    this.roleService.getUsersByRole(role).subscribe(users => this.users = users);
  }

  getUsersByName(name: string): void {

  }
}
