import { Component } from '@angular/core';

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
  private id:string;
  private sponsor:boolean;
  private uname:string;
  private surname:string;
  private province:string;
  private age:number; 
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private showMenu:boolean = true;
  private menuCollapse:boolean = true;

  showDropdown(typeMenu:string){
    if(typeMenu === "collapse"){
      this.menuCollapse=!this.menuCollapse;
    }else if(typeMenu === "loginMenu"){
      this.showMenu=!this.showMenu;
    }
  }
}
