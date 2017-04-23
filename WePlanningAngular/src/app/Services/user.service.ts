import { Injectable, Inject, forwardRef } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { PlanService } from './plan.service';
import { LoginService } from './login.service';
import { User } from '../Class/user.model';
import { Plan } from '../Class/plan.model';
import 'rxjs/Rx';


@Injectable()
export class UserService {
    private credentials: string;
    
    constructor(private http: Http, private loginService: LoginService) { }

    addUser(user: User) {
        return this.http.post("https://localhost:8443/api/user/", user)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }
    modifyUser(user: User, id: string) {
        this.credentials = this.loginService.getCredentials();
        let headers = new Headers();
        console.log(this.credentials);
        headers.append('Authorization', 'Basic ' + this.credentials);
        return this.http.put("https://localhost:8443/api/user/" + id, user, { headers: headers })
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    getUser(id: string) {
        return this.http.get("https://localhost:8443/api/user/" + id)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }
    getFriends(id: string) {
        return this.http.get("https://localhost:8443/api/user/" + id + "/friends")
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }
    isFriend(user: User) {
        let user_log: User;
        let friends: boolean;
        user_log=this.loginService.getUserLogged();
        friends = false;
        for (let i = 0; i < user_log.friends.length; i++) {
            if (user_log.friends[i].id === user.id) {
                friends = true;
            }
        }
        return friends;
    }

    private handleError(error: any) {
        console.error(error);
        switch (error.status) {
            case 409:
                return Observable.throw("Server error (" + error.status + "): El nombre de usuario ya esta en uso");
            case 404:
                return Observable.throw("Server error (" + error.status + "): Ha ocurrido algun error vuelva a intentarlo");
            case 401:
                return Observable.throw("Server error (" + error.status + "): No esta autorizado para realizar esa acción.");
            case 406:
                return Observable.throw("Server error (" + error.status + "): Rellene los campos correctamente 'Provincia' o 'Categoría' correctamente");
        }
    }
}