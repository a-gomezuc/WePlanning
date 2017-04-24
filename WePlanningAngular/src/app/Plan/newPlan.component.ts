import { Component } from '@angular/core';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';
import { Http } from '@angular/http';

import { Plan } from '../Class/plan.model';

import { PlanService } from '../Services/plan.service';

@Component({
    selector: 'app-root',
    templateUrl: './newPlan.component.html',
    styleUrls: ['../app.component.css']
})
export class NewPlanComponent {

    file: File;
    showPreview:boolean = false;
    constructor(private router: Router, private http: Http, private planService: PlanService) { }

    newPlan(title: string, category: string, date: string, place: string,
        address: string, prize: number, description: string) {
        let plan = new Plan(title, category, date, place, address, prize, description);
        this.planService.addPlan(plan).subscribe(
            plan => {
                console.log(plan);
                console.log(this.file);
                this.planService.selectImagePlan(this.file, plan.id).subscribe(
                    plan => this.planService.initIndexPlans()
                );
                this.router.navigate(['/index']);
            }
        );
    }

    changed(fileInput: any) {
        this.file = fileInput.target.files[0];
        /*this.fileReader.readAsDataURL(this.file);
        console.log(this.file);*/
        this.showPreview = true;
        
            var reader = new FileReader();

            reader.onload = () => {
                this.file = fileInput.target.result;
            }

            reader.readAsDataURL(fileInput.target.files[0]);
        
    }
}
