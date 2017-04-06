import { Component } from '@angular/core';

export class Plan{
  
  constructor(private id:number, 
  private title:string, 
  private category:string, 
  private author:string, 
  private place:string, 
  private address:string,
  private prize:number,
  private date: string){}

  
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
}
