import { Session } from './session';

export class Criteria {
  id: number;
  name: string;
  type: string;
  url: string;
  session: Array<Session>;


  constructor() {
    this.id = null;
    this.name ="";
    this.type="";
    this.url="";
    this.session= [];
  }
}
