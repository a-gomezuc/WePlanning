import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { User } from './app.component';

import 'rxjs/Rx';
@Injectable()
export class UserService {
    
    constructor( private http:Http){}

    addUser(user:User){
        return this.http.post("https://localhost:8443/api/user/",user)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }

    handleError(error: any){
        console.error(error);
        return Observable.throw("Server error (" + error.status + "): " + error.text());
    }
}