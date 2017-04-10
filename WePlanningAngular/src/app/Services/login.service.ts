import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { Http, Headers } from '@angular/http';

import { User } from '../app.component';
import { UserService } from '../Services/user.service';

import 'rxjs/Rx';

@Injectable()
export class LoginService {

    private isLogged:boolean = false;
    private user:User;
    private currentUser:User;


    constructor(private router: Router, private http: Http, private userService:UserService) { }

    getUserLogged(){
        return Observable.of(this.user);
    }

    setUserLogged(user:User){
        this.currentUser = user;
    }

    isUserLogged(){
        return this.isLogged;
    }

    private handleLogIn(response){
        this.isLogged = true;
        this.userService.getUser(response.json().id).subscribe(
            user => this.user = user
        );
    }

    private credentialsAuthenticationHeader (headers:Headers, username:string, password:string){
        headers.append('Authorization', 'Basic '+ btoa (username + ':' + password));
    }
    
    login(username: string, password: string) {
        let headers = new Headers();
        this.credentialsAuthenticationHeader(headers, username, password);
        return this.http.get("https://localhost:8443/api/login", {headers : headers})
            .map(response => {
                this.handleLogIn(response);
                return response.json();
            })
            .catch(error => this.handleError(error))
    }

    private handleError(error: any) {
        console.log(error);
        return Observable.throw("Server error (" + error.status + "): " + error.text())
    }
}