import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RemoveService {
  removeUserURL = "http://localhost:80/Lab-8/php/controller.php?";

  constructor(private httpClient: HttpClient) { }

  removeUser(email: string) {
    this.httpClient.get<String>(this.removeUserURL +
      "requestedAction=removeUser" + 
      "&email=" + email)
      .subscribe(result => console.log("Result from the RemoveService: ", result));
    console.log("From RemoveService: The request has been sent.");
  }
}
