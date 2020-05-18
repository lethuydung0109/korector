export class SonarResults {
    id : Number;
    bugs : String;
    vuls: String;
    debt: String;
    smells: String;
    coverage:String;
     dups: String;
    dups_block : String;
    date : String;
    note_finale: Number;
    project_id: Number;
    session_id: Number;

    constructor() {
        this.id = null;
        this.bugs= '';
        this.vuls='';
        this.debt='';
        this.smells= '';
        this.coverage= '';
        this.dups='';
        this.dups_block='';
        this.date = '';
        this.project_id = null;
        this.session_id= null;
        this.note_finale= null;

      }

}

