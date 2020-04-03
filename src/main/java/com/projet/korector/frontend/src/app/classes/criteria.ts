export class Criteria {
    private type: string;
    private name:string;
    private value: number;

    constructor(name: string)
    {
        this.type="static";
        this.name=name;
        this.value=0;

    }
}
