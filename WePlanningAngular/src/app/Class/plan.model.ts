
import { User } from './user.model';
import { Comment } from './comment.model';
export class Plan{

  public id:number;
  public asistents:User[] = [];
  public comments:Comment[] = [];
  
  constructor(
  public title:string, 
  public category:string,
  public date: string,
  public place:string,
  public address:string,
  public prize:number,
  public description:string,
  ){}

  public author:User;
  public imagePlanTitle:string;
  
  
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