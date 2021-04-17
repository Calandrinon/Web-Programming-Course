import { Component, OnInit } from '@angular/core';
import { UpdateService } from './service/update.service';
import { User } from '../model/user.model';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit {
  updated: boolean = false;

  constructor(private updateService: UpdateService) { }

  ngOnInit(): void {
  }

  updateUser(username: string, password: string) {
    console.log(username, password);
    console.log("Called the updateUser from the updateService...");
    this.updateService.updateUser(username, password);
  }
}
