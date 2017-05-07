import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { LoginService } from './login.service';

@Injectable()
export class RouterSecurity implements CanActivate {
  constructor(private router:Router, private loginService: LoginService) {}

  canActivate() {
    if(!this.loginService.isUserLogged()){
      this.router.navigate(['/index']);
    }
    return this.loginService.isUserLogged();
  }
}