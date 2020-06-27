export interface IEntreprise {
  id?: number;
  name?: string;
  url?: string;
}

export class Entreprise implements IEntreprise {
  constructor(public id?: number, public name?: string, public url?: string) {}
}
