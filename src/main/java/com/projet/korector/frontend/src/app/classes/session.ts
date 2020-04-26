import { Project } from './project';
import { Run } from './run';
import { Criteria } from './criteria';

export class Session {
    public id : number;
    public name:string;
    public date_depot : string;
    public heureDepot : string;
    public projects : Array<Project>;
    public runs : Array<Run>;
    public criterias : Array<Criteria>;

   

    constructor(name : string, date : string, heure:string)
    {
        this.id=null;
        this.name=name;
        this.date_depot=date;
        this.heureDepot=heure;
        this.projects=[];
        this.criterias=[];
        this.runs=[];        
    }
}
