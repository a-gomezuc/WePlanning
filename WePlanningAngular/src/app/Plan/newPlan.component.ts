import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Http } from '@angular/http';

import { Plan } from '../Class/plan.model';

import { PlanService } from '../Services/plan.service';

@Component({
  selector: 'app-root',
  templateUrl: './newPlan.component.html',
  styleUrls: ['../app.component.css']
})
export class NewPlanComponent {

    constructor(private router:Router, private http:Http, private planService: PlanService){}

    newPlan(title:string, category:string, date:string, place:string,
     address:string, prize:number, description:string){
        let plan = new Plan(title, category, date, place, address, prize, description);
        this.planService.addPlan(plan).subscribe(
            plan => {
                console.log (plan);
                this.router.navigate(['/index']);
            }
        );
    }
}
