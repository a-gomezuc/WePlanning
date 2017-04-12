import { Component, OnInit} from '@angular/core';
import { Http } from '@angular/http';

import { LoginService } from './Services/login.service';
import { PlanService } from './Services/plan.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit{

  private showMenu:boolean = true;
  private menuCollapse:boolean = true;


  constructor (private http:Http, private loginService:LoginService, private planService:PlanService){}

  

  ngOnInit(){
    this.planService.initIndexPlans();//Inicializa los planes que se mostraran al comienzo en el index
  }

  logIn(id:string, pass:string){
    this.loginService.login(id ,pass).subscribe(
      user => console.log(user)
    );
    this.planService.initFriendsPlans();//Inicializamos los planes de nuestros amiogos que se mostrarán en el index una vez logueados.
  }

  logOut(){
    this.loginService.logout().subscribe();
  }
  
  showDropdown(typeMenu:string){// Método encargado de los dropdowns del navbar
    if(typeMenu === "collapse"){
      this.menuCollapse=!this.menuCollapse;
    }else if(typeMenu === "loginMenu"){
      this.showMenu=!this.showMenu;
    }
  }
}
