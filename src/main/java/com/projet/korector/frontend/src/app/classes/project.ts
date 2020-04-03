import {Session} from './session';

export class Project {
  id: number;
  name: string;
  descripion: string;
  url: string;
  note: number;
  dateDepot: string;
  session: Session;

  constructor(name: string) {
    this.name = name;
  }


}
