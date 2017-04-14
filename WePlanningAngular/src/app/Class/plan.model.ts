

export class Plan{

  private id:number;
  constructor(
  private title:string, 
  private category:string,
  private place:string,
  private address:string,
  private prize:number,
  private date: string,
  ){}
  private description ="";
  private imagePlanTitle:string;
  
  
  getTitle(){
    return this.title;
  }
  
 }