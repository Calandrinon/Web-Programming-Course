import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../model/user.model';
import { UserListComponent } from "../../user-list/user-list.component";

@Injectable({
  providedIn: 'root'
})
export class NameService {
  usersByNameURL = "http://localhost:80/Lab-8/php/controller.php?";
  users: User[] = [];

  constructor(private httpClient: HttpClient) { }

  getUsersByName(name: string): Observable<User[]>{
    return this.httpClient.get<User[]>(this.usersByNameURL+ "requestedAction=getUserByName&name=" + name);
  }
}
