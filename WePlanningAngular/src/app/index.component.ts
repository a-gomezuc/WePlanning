import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Http } from '@angular/http';

import { PlanService } from './Services/plan.service';
import { LoginService } from './Services/login.service';

import { Plan } from './app.component';
import { User } from './app.component';
@Component({
  selector: 'app-root',
  templateUrl: './index.component.html',
  styleUrls: ['./app.component.css']
})
export class IndexComponent implements OnInit{

    private plans: Plan []=[];
    private friendPlans: Plan [] = [];
    constructor(private http:Http, private planService: PlanService, private loginService:LoginService){}

    ngOnInit(){
      this.update();
    }

    update(){
      this.planService.getPlans().subscribe(
        plans => this.plans = plans
      );
      if(this.loginService.isUserLogged()){
        this.planService.getFriendPlans().subscribe(
          friendPlans => this.friendPlans = friendPlans
        )
      }
    }

}