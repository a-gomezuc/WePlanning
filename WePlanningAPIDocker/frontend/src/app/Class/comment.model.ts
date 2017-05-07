import { User } from './user.model';
export class Comment{
    private id:number;
    constructor(
        private content:String,
        private date:String,
        
    ){}
    author:User;

    getAuthorId(){
    return this.author.getId();
  }
  getId(){
    return this.id;
  }
}
 
