import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UpdateService {
  updateUserURL = "http://localhost:80/Lab-8/php/controller.php?";

  constructor(private httpClient: HttpClient) { }

  updateUser(username: string, password: string) {
    this.httpClient.get<String>(this.updateUserURL +
      "requestedAction=updateUser" + 
      "&username=" + username +
      "&password=" + password)
      .subscribe(result => console.log("Result from the UpdateService: ", result));
    console.log("From UpdateService: The request has been sent.");
  }

}
