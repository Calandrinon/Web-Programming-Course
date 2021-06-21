import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../model/user.model';
import { UserListComponent } from "../../user-list/user-list.component";

@Injectable({
  providedIn: 'root'
})
export class RoleService {
  usersByRoleURL = "http://localhost:80/Lab-8/php/controller.php?";
  users: User[] = [];

  constructor(private httpClient: HttpClient) { }

  getUsersByRole(role: string): Observable<User[]>{
    return this.httpClient.get<User[]>(this.usersByRoleURL + "requestedAction=getUserByRole&role=" + role);
  }
}
