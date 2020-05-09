export class SessionCritere {

    public id : number;
    public critereId: number;
    public name: string;
    public sessionId : number ;
    public height: number;
    public value: number;
    public seuil: number;
    public type: string;

    constructor()
    {
        this.id=null;
        this.sessionId=null;
        this.seuil=0;
        this.value=0;
    }

}
