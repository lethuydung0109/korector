import { Project } from './project';
import { Run } from './run';
import { Criteria } from './criteria';

export class Session {
    public id : number;
    public name:string;
    public dateDepot : string;
    public projects : Array<Project>;
    public runs : Array<Run>;
   // public criteria : Array<Criteria>

   

    constructor(name : string, date : string)
    {
        this.id=null;
        this.name=name;
        this.dateDepot=date;
        this.projects=[];
        this.runs=[];
        //this.projects=projects;
       // this.criteria=[];
    }

    public setSession(newSession : Session) 
    {
        this.id=newSession.id;
        this.name=newSession.name;
        this.dateDepot=newSession.dateDepot;
        this.projects=newSession.projects;
        this.runs=newSession.runs;
    }

    public getId() : number
    { return this.id}



}
