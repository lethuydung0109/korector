import { Session } from 'protractor';

export class Run {
    public id : Number;
    public session : Session;

    constructor(session :Session)
    {
        this.id=null;
        this.session=session;
    }
}
