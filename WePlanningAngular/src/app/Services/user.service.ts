import { Injectable, Inject, forwardRef } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { PlanService } from './plan.service';
import { User } from '../Class/user.model';
import { Plan } from '../Class/plan.model';
import 'rxjs/Rx';


@Injectable()
export class UserService {

    constructor( private http:Http){}
    
    addUser(user:User){
        return this.http.post("https://localhost:8443/api/user/",user)
        .map(response => response.json())
        .catch(error => this.handleError(error));
    }

    getUser(id:string){
        return this.http.get("https://localhost:8443/api/user/"+id)
        .map( response => response.json())
        .catch( error => this.handleError(error));
    }
    
    handleError(error: any){
        console.error(error);
        return Observable.throw("Server error (" + error.status + "): " + error.text());
    }
}