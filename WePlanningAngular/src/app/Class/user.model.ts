import { Plan } from './plan.model';

export class User {

  private identifier:number;
  private description ="";
  private plans:Plan[] = [];
  constructor(
  private id:string,
  private sponsor:boolean,
  private uname:string,
  private surname:string,
  private uemail:string,
  private province:string,
  private age:number,
  private passwordHash:string){}

  public getId(){
    return this.id;
  }

  getpaswordHash(){
    return this.passwordHash;
  }
  
}