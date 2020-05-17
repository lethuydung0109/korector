import { Session } from './session';
import { User } from './user';
import {SonarResults} from '../classes/sonar-results';

export class Project {

  id: number;
  name: string;
  description: string;
  url: string;
  note: number;
  dateDepot: string;
  session: Array<Session>;
  user : User;
  sonarResults : SonarResults;
  runExitsForSessionProject : boolean;
  constructor(name: string) {
    this.name = name;
    this.id = null;
    this.description= '';
    this.url= "";
    this.note= 0;
    this.dateDepot= null;
    this.session= [];
    this.user=null;
    this.sonarResults=null;
    this.runExitsForSessionProject = null;

  }



}
