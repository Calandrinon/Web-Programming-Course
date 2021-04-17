import { Component, OnInit } from '@angular/core';
import { AddService } from './service/add.service';
import { User } from '../model/user.model';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {
  added: boolean = false;

  constructor(private addService: AddService) { }

  ngOnInit(): void {
  }

  addUser(name: string, username: string, password: string, dateOfBirth: string, role: string, email: string) {
    let user: User = {
      name, username, password, dateOfBirth, role, email
    };
    console.log(user);
    console.log("Called the addUser from the AddService...");
    this.addService.addUser(user);
  }
}
