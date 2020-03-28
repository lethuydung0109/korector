import { Project } from './project';
import { Criteria } from './criteria';

export class Session {
    public id : number;
    public name:string;
    public projects : Array<Project>
    public criteria : Array<Criteria>
    public dateDepot : Date;

    constructor(name : string)
    {
        this.id=null;
        this.name=name;
        this.projects=[];
        this.criteria=[];
        this.dateDepot=null;
    }

}
