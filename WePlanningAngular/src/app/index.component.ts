import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {Plan} from './app.component'
@Component({
  selector: 'app-root',
  templateUrl: './index.component.html',
  styleUrls: ['./app.component.css']
})
export class IndexComponent {
    private plan = new Plan(1,"Carrera","Deportes","Guillermo","Madrid","URJC Vicálvaro",12,"5-06-2017");
}