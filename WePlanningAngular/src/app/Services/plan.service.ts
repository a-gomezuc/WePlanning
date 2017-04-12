import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Headers } from '@angular/http';

import { Plan } from '../Class/plan.model';
import { LoginService } from './login.service';

import 'rxjs/Rx';

@Injectable()
export class PlanService {

    private credentials:string;
    private plans: Plan []= [];//Array que contiene todos los planes exitentes en la aplicación
    private friendPlans: Plan [] = [];//Array de los que contiene los planes de los amigos del usuario logueado.


    constructor(private http: Http, private loginService: LoginService) {}
    
    initIndexPlans(){//Introduce en el array de planes todos los planes que s emostrarán en el sistema.
        this.getApiPlans().subscribe(
            plans => this.plans = plans
        );
    }

    initFriendsPlans(){//Añade al array de planes 'friendPlans' los planes del usuario que se ha logueado en el sistema.
        this.getApiFriendPlans().subscribe(
            friendPlans => this.friendPlans = friendPlans
        );
    }

    getPlans(){
        return this.plans;
    }

    getFriendPlans(){
        return this.friendPlans;
    }
    getApiPlans() {
        return this.http.get("https://localhost:8443/api/plans/")
            .map(response => this.plans = response.json())
            .catch(error => this.handleError(error))
    }

    getApiFriendPlans() {
        this.credentials = this.loginService.getCredentials();
        let headers =new  Headers ();
        console.log(this.credentials);
        headers.append('Authorization', 'Basic ' + this.credentials);
        return this.http.get("https://localhost:8443/api/viewFriendsPlans",{headers : headers})
            .map(response => this.friendPlans = response.json())
            .catch(error => this.handleError(error))
    }
    private handleError(error: any) {
        console.log(error);
        return Observable.throw("Server error (" + error.status + "): " + error.text())
    }
}