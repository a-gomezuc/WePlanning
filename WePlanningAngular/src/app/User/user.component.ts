import { Component, OnInit } from '@angular/core';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';

import { UserService } from '../Services/user.service';

import { User } from '../Class/user.model';
import{ LoginService } from '../Services/login.service'

@Component({
  selector: 'app-root',
  templateUrl: './user.component.html',
  styleUrls: ['../app.component.css'],
})
export class UserComponent {

  user: User;
  userFriends: User[];

  constructor(private userService: UserService, private activatedRoute: ActivatedRoute, private loginService:LoginService) {
    let id = this.activatedRoute.snapshot.params['id'];
    this.userService.getUser(id).subscribe(
      User => {
        this.user = User;
        console.log(this.user);
      }
    );
    this.userService.getFriends(id).subscribe(
      userFriends => {
          this.userFriends=userFriends;
          console.log(this.userFriends);
      }
    )
  }
}