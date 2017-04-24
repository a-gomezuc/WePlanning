import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Headers } from '@angular/http';

import { Plan } from '../Class/plan.model';
import { Comment } from '../Class/comment.model';
import { User } from '../Class/user.model'
import { LoginService } from './login.service';
import { UserService } from './user.service';

import 'rxjs/Rx';

@Injectable()
export class CommentService {

    private credentials: string;
    private comments: Comment[] = [];


    constructor(private http: Http, private loginService: LoginService, private userService: UserService) { }

    initIndexPlans() {//Introduce en el array de planes todos los planes que s emostrarán en el sistema.
        this.getApiComments().subscribe(
           comments => this.comments = comments
        );
    }

    getcomments() {
        return this.comments;
    }


    getApiComments() {
        return this.http.get("https://localhost:8443/api/plans/comments")
            .map(response => { return response.json() })
            .catch(error => this.handleError(error))
    }
    getApiCommentById(id: number) {
        return this.http.get("https://localhost:8443/api/plans/"+ id+"/comment")
            .map(response => { return response.json() })
            .catch(error => this.handleError(error))
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