import { Component, NgModule } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import {Http} from '@angular/http';
import {User} from './app.component';
@Component({
  selector: 'app-root',
  templateUrl: './register.component.html',
  styleUrls: ['./app.component.css']
})
export class RegisterComponent {

  private sponsor = false;

  constructor(private http:Http){}
  newUser(id:string, uname:string, surname:string, uemail:string, province:string, age:number, passwordHash:string ){
    let user = new User (id, this.sponsor, uname, surname, uemail, province, age, passwordHash);

    this.http.post("https://localhost:8443/api/user/",user).subscribe(
      response => console.log(response),
      error => console.log(error)
    );
    console.log();
  }
}
