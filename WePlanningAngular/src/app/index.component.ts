import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import {Http} from '@angular/http';

import {Plan} from './app.component';
import {User} from './app.component';
@Component({
  selector: 'app-root',
  templateUrl: './index.component.html',
  styleUrls: ['./app.component.css']
})
export class IndexComponent implements OnInit{

    private plans: Plan []=[];
    constructor(private http:Http){}

    ngOnInit(){
      this.update();
    }

    private update(){
      this.http.get("https://localhost:8443/api/plans/").subscribe(
        response => {
          let data = response.json();
          console.log(response.json());
          for(let dat of data){
              this.plans.push(dat);
          }
        },
        error => console.log(error)
      );
    }

}