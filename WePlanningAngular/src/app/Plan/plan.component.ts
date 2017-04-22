import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import { PlanService } from '../Services/plan.service';
import { LoginService } from '../Services/login.service';
import { Plan } from '../Class/plan.model';
import { User } from '../Class/user.model';


@Component({
  selector: 'app-root',
  templateUrl: './plan.component.html',
  styleUrls: ['../app.component.css'],
})
export class PlanComponent {

  plan: Plan;
  loggedUserAssist: boolean;

  constructor(private router: Router, private planService: PlanService, private activatedRoute: ActivatedRoute, private loginService: LoginService) {
    let id = this.activatedRoute.snapshot.params['id'];
    this.planService.getApiPlanById(id).subscribe(
      plan => {
        this.plan = plan;
        console.log(this.plan);
      }
    );
    this.loggedUserAssist=false;
  }

  assistPlan(planAssist: Plan) {
    this.planService.assist(planAssist).subscribe(
      result => {
        this.loggedUserAssist = true;
        console.log(planAssist);
        this.planService.getApiPlanById(planAssist.id).subscribe(
          plan => {
            this.plan = plan;
            console.log(this.plan);
          }
        );
      },
      error=>{
        this.loggedUserAssist=true;
      }
    )
  }
}