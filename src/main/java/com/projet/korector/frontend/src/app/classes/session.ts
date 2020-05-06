import { Project } from './project';
import { Run } from './run';
import { Criteria } from './criteria';
import { User } from './user';

export class Session {
    public id : number;
    public name:string;
    public date_depot : string;
    public heureDepot : string;
    public projects : Array<number>;
    public criterias : Array<number>;
    public users : Array<User>;
    public runs : Array<Run>;

    constructor(name : string, date : string, heure:string)
    {
        this.id=null;
        this.name=name;
        this.date_depot=date;
        this.heureDepot=heure;
        this.projects=[];
        this.criterias=[];
        this.users=[];
        this.runs=[];        
    }
}
