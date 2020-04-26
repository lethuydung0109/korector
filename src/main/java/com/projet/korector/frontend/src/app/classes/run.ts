import { Session } from './session';

export class Run {
    public id : number;
    public session : Session;

    constructor(session : Session)
    {
        this.id=null;
        this.session=session;
    }
}
