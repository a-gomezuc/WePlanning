import { Component } from '@angular/core';
import { Http } from '@angular/http';

import { LoginService } from './Services/login.service';
import { PlanService } from './Services/plan.service';

export class Plan{

  private id:number; 
  private title:string; 
  private category:string; 
  private author:User; 
  private place:string;
  private address:string;
  private prize:number;
  private date: string;
  
  setAuthor(user:any){
    this.author= user as User;
  }
}

export class User {

  private identifier:number;
  private description ="";
  constructor(
  private id:string,
  private sponsor:boolean,
  private uname:string,
  private surname:string,
  private uemail:string,
  private province:string,
  private age:number,
  private passwordHash:string){}
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  private showMenu:boolean = true;
  private menuCollapse:boolean = true;


  constructor (private http:Http, private loginService:LoginService, private planService:PlanService){}

  

  logIn(id:string, pass:string){
    this.loginService.login(id ,pass).subscribe(
      user => console.log(user)
    );
  }
  showDropdown(typeMenu:string){
    if(typeMenu === "collapse"){
      this.menuCollapse=!this.menuCollapse;
    }else if(typeMenu === "loginMenu"){
      this.showMenu=!this.showMenu;
    }
  }
}
