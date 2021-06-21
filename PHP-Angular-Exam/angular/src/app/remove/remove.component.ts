import { Component, OnInit } from '@angular/core';
import { RemoveService } from './service/remove.service';
import { User } from '../model/user.model';

@Component({
  selector: 'app-remove',
  templateUrl: './remove.component.html',
  styleUrls: ['./remove.component.css']
})
export class RemoveComponent implements OnInit {
  removed: boolean = false;

  constructor(private removeService: RemoveService) { }

  ngOnInit(): void {
  }

  removeUser(email: string) {
    console.log(email);
    console.log("Called the removeUser from the removeService...");
    this.removeService.removeUser(email);
    this.removed = true;
  }

}
