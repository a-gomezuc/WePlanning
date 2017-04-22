import { Plan } from './plan.model';

export class User {

  public identifier: number;
  public description = "";
  public plans: Plan[] = [];
  public friends: User[] = [];
  constructor(
    public id: string,
    public sponsor: boolean,
    public uname: string,
    public surname: string,
    public uemail: string,
    public province: string,
    public age: number,
    public passwordHash: string) { }

  getId() {
    return this.id;
  }

  getpaswordHash() {
    return this.passwordHash;
  }

}