import { Component } from '@angular/core';
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

export class IndexComponent{

    constructor(private http:Http, private planService: PlanService, private loginService:LoginService){}


}