import { Session } from 'protractor';

export class Project {

    public id : Number;
    public name : string;
    public note : Number;
    public sessions : Array<Session>
    //private gitUrl: string

   

    constructor(name : string, note :Number)
    {
        this.id=null;        
        this.name=name;
        this.note=note;
        this.sessions=[];
        // this.gitUrl="";
        // this.notes=[];
    }
}
