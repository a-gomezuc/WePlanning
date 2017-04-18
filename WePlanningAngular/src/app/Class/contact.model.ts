export class Contact{
    private id:number;
    constructor(
        private firstName:String,
        private lastName:String,
        private phone:number,
        private emailAddress:String,
        private company:String,
        private message:String
    ){}
}