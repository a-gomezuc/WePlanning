import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import { Http, Headers } from '@angular/http';

import { User } from '../Class/user.model';
import { ColisionService } from '../Services/colision.service';

import 'rxjs/Rx';

@Injectable()
export class LoginService {

    private isLogged: boolean = false;//Variable con la cual sabremos si el uisuario esta logeuado o no el sistema.
    private credentials: string;//Credenciales del usuario (Encriptadas).
    private userLogged: User;//Usuario logueado actuamente en el sistema
    private userLoggedFriends: User[];

    constructor(private router: Router, private http: Http, private colisionService: ColisionService) { }

    getCredentials() {// Obtencion de las cedenciales del usuario
        return this.credentials;
    }

    getUserLogged() {
        return this.userLogged;
    }
    getUserLoggedFriends() {
        return this.userLoggedFriends;
    }
    getUserLoggedId() {
        return this.userLogged.getId();
    }
    setUserLoggedFriends(friends: User[]){
        this.userLoggedFriends=friends;
    }
    setCredentials(credentials: string) {
        this.credentials = credentials;
    }

    isUserLogged() {//
        return this.isLogged;
    }
    isThisUserLogged(user:User) {//
        return (this.userLogged.id==user.id);
    }
    

    private handleLogIn(response) {
        this.isLogged = true;
        this.colisionService.getUser(response.json().id).subscribe(
            user => this.userLogged = user
        );
        this.colisionService.getFriends(response.json().id).subscribe(
            userFriends => this.userLoggedFriends = userFriends
        );
    }

    login(username: string, password: string) {
        if (username !== "") {
            let headers = new Headers();//Creación de la cabecera que le tenemos que pasar al método para que nos loguee correctamente.
            this.credentials = btoa(username + ':' + password);//Encriptación de las credenciales del usuario.
            headers.append('Authorization', 'Basic ' + this.credentials);//Añadimos  a la cabecera las credenciales.
            return this.http.get("https://localhost:8443/api/login", { headers: headers })
                .map(response => {
                    this.handleLogIn(response);
                    localStorage.setItem("user", response.json());
                    return response.json();
                })
                .catch(error => this.handleError(error))
        } else {
            return Observable.throw("Server error (401): Introduzca correctamente sus datos de usuario.");
        }

    }
    logout() {
        let headers = new Headers();
        headers.append('Authorization', 'Basic ' + this.credentials);
        console.log(this.credentials);
        return this.http.get("https://localhost:8443/api/logout", { headers: headers })
            .map(response => {
                this.isLogged = false;
                this.userLogged = null;
                localStorage.clear();
                return true;
            })
            .catch(error => this.handleError(error))
    }

    private handleError(error: any) {
        console.error(error);
        switch (error.status) {
            case 409:
                return Observable.throw("Server error (" + error.status + "): El nombre de usuario ya esta en uso");
            case 404:
                return Observable.throw("Server error (" + error.status + "): Ha ocurrido algun error vuelva a intentarlo");
            case 401:
                return Observable.throw("Server error (" + error.status + "): Introduzca correctamente sus datos de usuario.");
        }
    }
}