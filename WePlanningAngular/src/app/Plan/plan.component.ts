import { Component, OnInit } from '@angular/core';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';


import { PlanService } from '../Services/plan.service';
import { LoginService} from '../Services/login.service';
import { Plan } from '../Class/plan.model';


@Component({
  selector: 'app-root',
  templateUrl: './plan.component.html',
  styleUrls: ['../app.component.css'],
})
export class PlanComponent {

  plan: Plan;

  constructor(private planService: PlanService, private activatedRoute: ActivatedRoute, private loginService:LoginService) {
    let id = this.activatedRoute.snapshot.params['id'];
    //Ésta sería la forma eficiente de hacerlo pero da error.
    /*this.planService.getApiPlanById(id).subscribe(
      plan => {
        this.plan = plan;
        console.log(this.plan);
      }
    );*/
    this.plan = this.planService.getPlans()[id-1];
  }
  sameAuthor(){
    return (this.loginService.getUserLoggedId()===this.plan.getAuthorId());
  }
}