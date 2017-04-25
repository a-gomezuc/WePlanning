import { Injectable, Inject, forwardRef } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';

import { PlanService } from './plan.service';
import { LoginService } from './login.service';
import { User } from '../Class/user.model';
import { Plan } from '../Class/plan.model';
import 'rxjs/Rx';


@Injectable()
export class UserService {
    private credentials: string;
    private userS: User;
    private userFriendsS: User[];
    
    constructor(private http: Http, private loginService: LoginService, private router: Router) { }

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
    addFriend(user:User, id:string){
        this.credentials = this.loginService.getCredentials();
        let headers = new Headers();
        console.log(this.credentials);
        headers.append('Authorization', 'Basic ' + this.credentials);
        return this.http.put("https://localhost:8443/api/user/friend/"+ id,user,{ headers: headers })
            .map(response => response.json())
            .catch(error => this.handleError(error));

    }
    deleteFriend(id:string){
        this.credentials = this.loginService.getCredentials();
        let headers = new Headers();
        console.log(this.credentials);
        headers.append('Authorization', 'Basic ' + this.credentials);
        return this.http.delete("https://localhost:8443/api/user/friend/"+ id,{ headers: headers })
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }

    getUser(id: string) {
        return this.http.get("https://localhost:8443/api/user/" + id)
            .map(response => {return this.userS=response.json()})
            .catch(error => this.handleError(error));
    }
    getFriends(id: string) {
        return this.http.get("https://localhost:8443/api/user/" + id + "/friends")
            .map(response => {return this.userFriendsS=response.json()})
            .catch(error => this.handleError(error));
    }
    getUserS() {
        return this.userS;
    }
    getFriendsS() {
        return this.userFriendsS;
    }
    setUserS(newUser:User){
        this.userS=newUser;
    }
    setUserFriendsS(newFriendsS:User[]){
        this.userFriendsS=newFriendsS;
    }
    isFriend(user: User) {
        let user_log: User;
        let friends: boolean;
        user_log=this.loginService.getUserLogged();
        friends = false;
        if(this.loginService.getUserLoggedFriends()!=undefined){
        for (let i = 0; i < this.loginService.getUserLoggedFriends().length; i++) {
            console.log(this.loginService.getUserLoggedFriends()[i].id+ "="+user.id);
            if (this.loginService.getUserLoggedFriends()[i].id === user.id) {
                friends = true;
            }
        }
        }
        return friends;
    }
    recharge(idNavigate:string){
    this.router.navigate(['/profile/'+idNavigate]);
    this.getUser(idNavigate).subscribe(
      User => {
        this.userS = User;
        console.log(this.userS);
      }
    );
    this.getFriends(idNavigate).subscribe(
      userFriends => {
          this.userFriendsS=userFriends;
          console.log(this.userFriendsS);
      }
    )
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