import { Component, OnInit } from '@angular/core';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';

import { UserService } from '../Services/user.service';
import { PlanService } from '../Services/plan.service';
import { User } from '../Class/user.model';
import{ LoginService } from '../Services/login.service'

@Component({
  selector: 'app-root',
  templateUrl: './user.component.html',
  styleUrls: ['../app.component.css'],
})
export class UserComponent {

  user: User;
  userFriends: User[];

  constructor(private userService: UserService, private router:Router,private activatedRoute: ActivatedRoute, private loginService:LoginService,private planService:PlanService) {
    let id = this.activatedRoute.snapshot.params['id'];
    this.userService.getUser(id).subscribe(
      User => {
        this.user = User;
        console.log(this.user);
      }
    );
    this.userService.setUserS(this.user);
    this.userService.getFriends(id).subscribe(
      userFriends => {
          this.userFriends=userFriends;
          console.log(this.userFriends);
      }
    )
    this.userService.setUserFriendsS(this.userFriends);
  }

  isSame(user:User){
    let isSame=false;
    if (this.loginService.isUserLogged() && user!=undefined){
      isSame=(this.loginService.getUserLogged().id===user.id)
    }
    return isSame;
  }
  recharge(idNavigate:string){
    this.router.navigate(['/profile/'+idNavigate]);
    this.userService.recharge(idNavigate);
    this.user=this.userService.getUserS();
    this.userFriends=this.userService.getFriendsS();
  }
  addFriendAndRoute(user:User, id:string){
    this.userService.addFriend(user, id).subscribe(
      userSubs => { this.user=userSubs;
    this.userService.getFriends(this.loginService.getUserLogged().id).subscribe(
      userFriendsSubs => { console.log("Nuevos amigos");
                          console.log(userFriendsSubs);
          this.loginService.setUserLoggedFriends(userFriendsSubs);
      }
    )
      }
    );
    this.planService.initFriendsPlans();
    this.recharge(id);
  }
  deleteFriendAndRoute(id:string){
    this.userService.deleteFriend(id).subscribe(
      userFriendsSubs => { console.log("Nuevos amigos ELIMINANDO");
        console.log(userFriendsSubs);
         this.loginService.setUserLoggedFriends(userFriendsSubs);
      }
    );
    this.planService.initFriendsPlans();
    this.recharge(id);
  }

  searchUsers(filter:string, usearch:string){
      this.userService.searchUsersBy(filter, usearch).subscribe();
    }
  
  
}