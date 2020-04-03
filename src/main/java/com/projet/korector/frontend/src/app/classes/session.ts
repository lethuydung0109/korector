import { Project } from './project';
import { Criteria } from './criteria';
import { Run } from './run';

export class Session {
    public id : number;
    public name:string;
    public dateDepot : Date;
    public projects : Array<Project>;
    public runs : Array<Run>;
   // public criteria : Array<Criteria>
    

    constructor(name : string, projects :Array<Project>)
    {
        this.id=null;
        this.name=name;
        this.dateDepot=null;
        this.projects=projects;
        this.runs=[];
        //this.projects=projects;
       // this.criteria=[];
        
    }

}
