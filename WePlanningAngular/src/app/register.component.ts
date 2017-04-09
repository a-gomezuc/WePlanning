import { Component, NgModule } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { UserService } from './user.service';

import { Http } from '@angular/http';
import { User } from './app.component';
@Component({
  selector: 'app-root',
  templateUrl: './register.component.html',
  styleUrls: ['./app.component.css']
})
export class RegisterComponent {

  private sponsor = false;

  constructor(private http:Http, private userService:UserService){}
  newUser(id:string, uname:string, surname:string, uemail:string, province:string, age:number, passwordHash:string ){
    let user = new User (id, this.sponsor, uname, surname, uemail, province, age, passwordHash);

    this.userService.addUser(user).subscribe(
      result => console.log(user)
    );
  }
}
