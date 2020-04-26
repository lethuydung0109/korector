import { Session } from './session';

export class Criteria {
  id: number;
  name: string;
  type: string;
  url: string;
  value: number;
  session: Array<Session>;


  constructor() {
    this.id = null;
    this.name ="";
    this.type="";
    this.url="";
    this.value=0;
    this.session= [];
  }
}
