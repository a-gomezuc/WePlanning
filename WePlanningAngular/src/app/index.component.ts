import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Http } from '@angular/http';

import {PlanService } from './plan.service';

import { Plan } from './app.component';
import { User } from './app.component';
@Component({
  selector: 'app-root',
  templateUrl: './index.component.html',
  styleUrls: ['./app.component.css']
})
export class IndexComponent implements OnInit{

    private plans: Plan []=[];
    constructor(private http:Http, private planService: PlanService){}

    ngOnInit(){
      this.update();
    }

    private update(){
      this.planService.getPlans().subscribe(
        plans => this.plans = plans
      );
    }

}