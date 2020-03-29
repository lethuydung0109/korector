export class Project {

    private name : string;
    private notes : Array<Number>;
    private gitUrl: string

    constructor(name : string)
    {
        this.name=name;
        this.gitUrl="";
        this.notes=[];
    }
}
