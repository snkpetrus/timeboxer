import{Component, OnInit}from '@angular/core';
import {User}from '../user.ts';
import {USERS} from '../mock-users.ts';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {
  users = USERS;
  selectedUser: User;

  constructor() { }

  ngOnInit() {
  }

  onSelect(user: User): void {
    this.selectedUser = user;
  }
}
