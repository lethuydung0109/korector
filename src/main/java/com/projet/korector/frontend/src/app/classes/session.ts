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

}
