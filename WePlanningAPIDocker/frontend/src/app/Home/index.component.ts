import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Http } from '@angular/http';

import { PlanService } from '../Services/plan.service';
import { LoginService } from '../Services/login.service';

import { Plan } from '../Class/plan.model';
import { User } from '../Class/user.model';
@Component({
  selector: 'app-root',
  templateUrl: './index.component.html',
  styleUrls: ['../app.component.css']
})

export class IndexComponent{

    constructor(private http:Http, private planService: PlanService, private loginService:LoginService){
      this.planService.initIndexPlans();
      if (this.loginService.isUserLogged()){
      this.planService.initFriendsPlans();
      }
    }

    search(title:string, category:string, place:string){
      this.planService.searchPlansBy(title,category,place).subscribe();
    }


}