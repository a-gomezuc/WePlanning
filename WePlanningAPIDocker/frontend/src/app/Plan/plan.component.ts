import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';


import { PlanService } from '../Services/plan.service';
import { CommentService } from '../Services/comment.service';
import { LoginService } from '../Services/login.service';
import { Plan } from '../Class/plan.model';
import { User } from '../Class/user.model';
import { Comment } from '../Class/comment.model';


@Component({
  selector: 'app-root',
  templateUrl: './plan.component.html',
  styleUrls: ['../app.component.css'],
})
export class PlanComponent {

  plan: Plan;

  constructor(private router: Router, private planService: PlanService, private activatedRoute: ActivatedRoute, private loginService: LoginService) {
    let id = this.activatedRoute.snapshot.params['id'];
    this.planService.getApiPlanById(id).subscribe(
      plan => {
        this.plan = plan;
        console.log(this.plan);
      }
    );
  }

  assistPlan(planAssist: Plan) {
    this.planService.assist(planAssist).subscribe(
      result => {
        console.log(planAssist);
        this.planService.getApiPlanById(planAssist.id).subscribe(
          plan => {
            this.plan = plan;
            console.log(this.plan);
          }
        );
      },
      error => {
        alert(error);
      }
    )
  }
  newComments(content: string, date: string) {
    let comments = new Comment(content, date);
    let id = this.plan.id;
    this.planService.addcomment(this.plan, comments).subscribe(
      Comment => {
        console.log(comments);
        this.planService.getApiPlanById(id).subscribe(
          plan => {
            this.plan = plan;
          }
        )
      }
    );
  }
}