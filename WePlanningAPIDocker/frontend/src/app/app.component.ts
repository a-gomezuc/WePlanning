import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Router } from '@angular/router';

import { LoginService } from './Services/login.service';
import { PlanService } from './Services/plan.service';
import { UserService } from './Services/user.service';
import { User } from './Class/user.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {

  private showMenu: boolean = true;
  private menuCollapse: boolean = true;
  private userLogged: User;


  constructor(private router:Router, private http: Http, private loginService: LoginService, private planService: PlanService, private userService:UserService) {
    this.planService.initIndexPlans();//Inicializa los planes que se mostraran al comienzo en el index
  }




  logIn(id: string, pass: string) {
    this.loginService.login(id, pass).subscribe(
      user => {
      this.userLogged = user;
        console.log(this.userLogged);
      },
      error => alert(error)
    );
    this.planService.initIndexPlans();
    this.planService.initFriendsPlans();//Inicializamos los planes de nuestros amigos que se mostrarán en el index una vez logueados.ng
    this.router.navigate(['/index']);
  }

  logOut() {
    this.loginService.logout().subscribe(
      error => {
        console.log(error);
      });
      this.router.navigate(['/index']);
  }

  showDropdown(typeMenu: string) {// Método encargado de los dropdowns del navbar
    if (typeMenu === "collapse") {
      this.menuCollapse = !this.menuCollapse;
    } else if (typeMenu === "loginMenu") {
      this.showMenu = !this.showMenu;
    }
  }
}
