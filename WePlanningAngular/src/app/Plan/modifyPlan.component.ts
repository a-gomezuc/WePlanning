import { Component } from '@angular/core';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';

import { Plan } from '../Class/plan.model';

import { PlanService } from '../Services/plan.service';
import { UserService } from '../Services/user.service';
import { LoginService } from '../Services/login.service';

@Component({
    selector: 'app-root',
    templateUrl: './modifyPlan.component.html',
    styleUrls: ['../app.component.css']
})

export class ModifyPlanComponent {

    private plan: Plan;

    constructor(private router: Router, private planService: PlanService, private activatedRoute: ActivatedRoute, private loginService: LoginService) {
        let id = this.activatedRoute.snapshot.params['id'];
        this.planService.getApiPlanById(id).subscribe(
            plan => {
                this.plan = plan;
                console.log(this.plan);
            }
        );
    }
    modifyPlan(planModified:Plan, title:string, category:string, place:string, date:string, address:string, prize:number, description:string){
        let id = this.plan.id;
        planModified.title= title;
        planModified.category = category;
        planModified.place = place;
        planModified.date = date;
        planModified.address = address;
        planModified.prize = prize;
        planModified.description = description;
        this.planService.modifyPlan(planModified).subscribe(
            plan => {
                console.log(plan);
                this.router.navigate(['/plan/'+id]);
            },
            error => console.log(error)
        );
    }

}