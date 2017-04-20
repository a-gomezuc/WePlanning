
import { User } from './user.model';
export class Plan{

  private id:number;
  private asistents:User[] = [];
  constructor(
  private title:string, 
  private category:string,
  private date: string,
  private place:string,
  private address:string,
  private prize:number,
  private description:string,
  ){}
  private author:User;
  private imagePlanTitle:string;
  
  
  getTitle(){
    return this.title;
  }
  getAuthorId(){
    return this.author.getId();
  }
  getAsistents(){
    return this.asistents;
  }
  getId(){
    return this.id;
  }
  
 }