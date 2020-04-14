import { Session } from './session';

export class Project {

  id: number;
  name: string;
  description: string;
  url: string;
  note: number;
  dateDepot: string;
  session: Array<Session>;

  constructor(name: string) {
    this.name = name;
    this.id = null;
    this.description= '';
    this.url= "";
    this.note= 0;
    this.dateDepot= null;
    this.session= [];
  }



}
