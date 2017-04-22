import { Component, OnInit } from '@angular/core';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';

import { UserService } from '../Services/user.service';

import { User } from '../Class/user.model';
import{ LoginService } from '../Services/login.service'

@Component({
  selector: 'app-root',
  templateUrl: './modifyUser.component.html',
  styleUrls: ['../app.component.css'],
})
export class ModifyUserComponent {

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
  }
  modifyUserParams(user:User, newName:string, newAge:number, newProvince:string, newEmail:string, newDescription:string){
    user.uname=newName;
    user.age=newAge;
    user.province=newProvince;
    user.uemail=newEmail;
    user.description=newDescription;
    this.userService.modifyUser(user, user.id).subscribe(
      newUser =>{
        this.user=newUser;
        console.log(newUser);
      }

    )

  }
}