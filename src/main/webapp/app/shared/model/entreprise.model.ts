export interface IEntreprise {
  id?: number;
  name?: string;
  email?: string;
  phoneNumber?: string;
}

export class Entreprise implements IEntreprise {
  constructor(public id?: number, public name?: string, public email?: string, public phoneNumber?: string) {}
}
