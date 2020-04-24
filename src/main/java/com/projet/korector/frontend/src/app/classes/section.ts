export class Section {
  id: number;
  name: string;
  startYear: number;
  endYear: number;
  constructor(name: string, start: number, end: number) {
    this.name = name;
    this.startYear = start;
    this.endYear = end;
  }

}