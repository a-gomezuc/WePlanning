import { Component, OnInit } from '@angular/core';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';

import { PlanService } from '../Services/plan.service';

import { Plan } from '../Class/plan.model';


@Component({
  selector: 'app-root',
  templateUrl: './plan.component.html',
  styleUrls: ['../app.component.css'],
})
export class PlanComponent {

  plan: Plan;

  constructor(private planService: PlanService, private activatedRoute: ActivatedRoute) {
    let id = this.activatedRoute.snapshot.params['id'];
    /*this.planService.getApiPlanById(id).subscribe(
      plan => {
        this.plan = plan;
        console.log(this.plan);
      }
    );*/
    this.plan = this.planService.getPlans()[id-1];
  }
}