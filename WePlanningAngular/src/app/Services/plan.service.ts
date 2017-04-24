import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Headers } from '@angular/http';

import { Plan } from '../Class/plan.model';
import { User } from '../Class/user.model';
import { Comment } from '../Class/comment.model';
import { LoginService } from './login.service';
import { UserService } from './user.service';
import { CommentService } from './comment.service';


import 'rxjs/Rx';

@Injectable()
export class PlanService {

    private credentials: string;
    private plans: Plan[] = [];//Array que contiene todos los planes exitentes en la aplicación
    private friendPlans: Plan[] = [];//Array de los que contiene los planes de los amigos del usuario logueado.


    constructor(private http: Http, private loginService: LoginService, private userService: UserService) { }

    initIndexPlans() {//Introduce en el array de planes todos los planes que s emostrarán en el sistema.
        this.getApiPlans().subscribe(
            plans => this.plans = plans
        );
    }

    initFriendsPlans() {//Añade al array de planes 'friendPlans' los planes del usuario que se ha logueado en el sistema.
        this.getApiFriendPlans().subscribe(
            friendPlans => this.friendPlans = friendPlans
        );
    }
    getPlans() {
        return this.plans;
    }

    getFriendPlans() {
        return this.friendPlans;
    }

    getApiPlans() {
        return this.http.get("https://localhost:8443/api/plans/")
            .map(response => { return response.json() })
            .catch(error => this.handleError(error))
    }

    getApiPlanById(id: number) {
        return this.http.get("https://localhost:8443/api/plans/" + id)
            .map(response => { return response.json() })
            .catch(error => this.handleError(error))
    }

    searchPlansBy(title: string, category: string, place: string) {
        return this.http.get("https://localhost:8443/api/plans/searchPlans/title=" + title +
            "/category=" + category + "/place=" + place)
            .map(response => { return this.plans = response.json() })
            .catch(error => this.handleError(error))
    }

    getApiFriendPlans() {
        this.credentials = this.loginService.getCredentials();
        let headers = new Headers();
        console.log(this.credentials);
        headers.append('Authorization', 'Basic ' + this.credentials);
        return this.http.get("https://localhost:8443/api/viewFriendsPlans", { headers: headers })
            .map(response => { return response.json() })
            .catch(error => this.handleError(error))
    }
    assist(plan:Plan){
        this.credentials = this.loginService.getCredentials();
        let headers = new Headers();
        let id= plan.id;
        console.log("https://localhost:8443/api/plans/"+id+"/assist");
        headers.append('Authorization', 'Basic ' + this.credentials);
        return this.http.put("https://localhost:8443/api/plans/"+id+"/assist",plan,{headers: headers})
            .map(response => response.json())
            .catch(error => this.handleError(error))
    }
    addcomment(plan:Plan, comment:Comment){
        this.credentials = this.loginService.getCredentials();
        let headers = new Headers();
        let id= plan.id;
        console.log(this.credentials);
        console.log(comment)
        headers.append('Authorization', 'Basic ' + this.credentials);
        return this.http.post("https://localhost:8443/api/plans/"+id+"/comment",comment,{headers: headers})
            .map(response => response.json())
            .catch(error => this.handleError(error))
    }

     selectImagePlan(file:File, id:number){
        this.credentials = this.loginService.getCredentials();
        let headers = new Headers();
        console.log(this.credentials);
        let formData = new FormData();
        formData.append('file', file); 
        headers.append('Authorization', 'Basic ' + this.credentials);
        return this.http.put("https://localhost:8443/api/plans/" + id + "/modifyPlanPhoto", formData, {headers:headers})
            .map(response => response.json())
            .catch(error => this.handleError(error))
    }

    modifyPlan(plan:Plan){
        let id = plan.id;
        this.credentials = this.loginService.getCredentials();
        let headers = new Headers();
        headers.append('Authorization', 'Basic ' + this.credentials);
        console.log("modifica plan");
        return this.http.put("https://localhost:8443/api/plans/" + id, plan, { headers: headers })
            .map(response => response.json())
            .catch(error => this.handleError(error))
    }

    isAsistent(plan:Plan, userAsistent:User){
        let assist=false;
        if(plan!=undefined){
                for(let i=0; i<plan.asistents.length; i++){
                    if(plan.asistents[i].id===userAsistent.id){
                        assist=true;
                    }
                }
            }
        return assist;
    }

    isAuthor(plan:Plan, id:string){
        let author = false;
        if(plan != undefined){
            if(plan.author.id === id){
                author = true;
            }
        }
        return author;
    }

    addPlan(plan:Plan) {
        this.credentials = this.loginService.getCredentials();
        let headers = new Headers();
        console.log(this.credentials);
        console.log(plan);
        headers.append('Authorization', 'Basic ' + this.credentials);
        return this.http.post("https://localhost:8443/api/plans/addPlan", plan, {headers: headers})
            .map(response => response.json())
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
                return Observable.throw("Server error (" + error.status + "): No esta autorizado para realizar esa acción.");
            case 406:
                return Observable.throw("Server error (" + error.status + "): Rellene los campos correctamente 'Provincia' o 'Categoría' correctamente");
        }
    }
}