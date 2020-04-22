import { Project } from './project';
import { Run } from './run';
import { Criteria } from './criteria';

export class Session {
    public id : number;
    public name:string;
    public date_depot : string;
    public projects : Array<Project>;
    public runs : Array<Run>;
    public criterias : Array<Criteria>;

   

    constructor(name : string, date : string)
    {
        this.id=null;
        this.name=name;
        this.date_depot=date;
        this.projects=[];
        this.criterias=[];
        this.runs=[];
        
    }

    public setSession(newSession : Session) 
    {
        this.id=newSession.id;
        this.name=newSession.name;
        this.date_depot=newSession.date_depot;
        this.projects=newSession.projects;
        this.criterias=newSession.criterias;
        this.runs=newSession.runs;
    }

    public getId() : number
    { return this.id}



}
