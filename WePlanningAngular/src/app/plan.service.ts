import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http } from '@angular/http';

import 'rxjs/Rx';

@Injectable()
export class PlanService {
     constructor (private http: Http){}

     getPlans (){
         return this.http.get("https://localhost:8443/api/plans/")
         .map(response => response.json())
         .catch(error => this.hadnleError(error))
     }

     private hadnleError(error:any){
         console.log(error);
         return Observable.throw("Server error ("+ error.status +"): "+error.text())
     }
}