import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { Http, Headers } from '@angular/http';

import { User } from '../app.component';
import { UserService } from '../Services/user.service';

import 'rxjs/Rx';

@Injectable()
export class LoginService {

    private isLogged:boolean = false;//Variable con la cual sabremos si el uisuario esta logeuado o no el sistema.
    private credentials:string;//Credenciales del usuario (Encriptadas).
    private user :User;//Usuario logueado actuamente en el sistema


    constructor(private router: Router, private http: Http, private userService:UserService) { }

    getCredentials(){// Obtencion de las cedenciales del usuario
        return this.credentials;
    }

    setCredentials(credentials:string){
        this.credentials = credentials;
    }

    isUserLogged(){//
        return this.isLogged;
    }

    private handleLogIn(response){
        this.isLogged = true;
        this.userService.getUser(response.json().id).subscribe(
            user => this.user = user
        );
    }
    
    login(username: string, password: string) {
        let headers = new Headers();//Creación de la cabecera que le tenemos que pasar al método para que nos loguee correctamente.
        this.credentials = btoa(username + ':' + password);//Encriptación de las credenciales del usuario.
        headers.append('Authorization', 'Basic ' + this.credentials);//Añadimos  a la cabecera las credenciales.
        return this.http.get("https://localhost:8443/api/login", {headers : headers})
            .map(response => {
                this.handleLogIn(response);
                localStorage.setItem("user", response.json());
                console.log(this.credentials);
                return response.json();
            })
            .catch(error => this.handleError(error))
    }

    logout(){
        let headers = new Headers();
        headers.append('Authorization', 'Basic ' + this.credentials);
        console.log(this.credentials);
        return this.http.get("https://localhost:8443/api/logout", {headers:headers})
        .map(response =>{
            this.isLogged = false;
            localStorage.removeItem("user");
            return true;
        })
        .catch (error => this.handleError(error))
    }

    private handleError(error: any) {
        console.log(error);
        return Observable.throw("Server error (" + error.status + "): " + error.text())
    }
}