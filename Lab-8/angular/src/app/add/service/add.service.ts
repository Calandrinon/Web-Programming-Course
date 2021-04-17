import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AddService {
  addUserURL = "http://localhost:80/Lab-8/php/controller.php?";

  constructor(private httpClient: HttpClient) { }

  addUser(user: User): void {
    console.log("From AddService: making the request");
    this.httpClient.get<String>(this.addUserURL+
      "requestedAction=addUser" + 
      "&name=" + user.name+
      "&username=" + user.username+
      "&password=" + user.password+
      "&dateOfBirth=" + user.dateOfBirth +
      "&role=" + user.role+
      "&email=" + user.email
      ).subscribe(stuff => console.log("From the subscribed Observable: ", stuff));
    console.log("From AddService: The request has been sent.");
  }
}
