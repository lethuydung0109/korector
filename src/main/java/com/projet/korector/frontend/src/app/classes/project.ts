import { Session } from './session';

export class Project {
  id: number;
  name: string;
  descripion: string;
  url: string;
  note: number;
  dateDepot: string;
  session: Array<Session>;

  constructor(name: string) {
    this.name = name;
    this.id = null;
    this.descripion="";
    this.url="";
    this.note=0;
    this.dateDepot="";
    this.session=[];
  }



}
