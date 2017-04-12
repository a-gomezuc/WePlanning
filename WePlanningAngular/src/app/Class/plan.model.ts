import { User } from './user.model';

export class Plan{

  private id:number; 
  private title:string; 
  private category:string; 
  private author:User; 
  private place:string;
  private address:string;
  private prize:number;
  private date: string;
  
  setAuthor(user:any){
    this.author= user as User;
  }
  
 }