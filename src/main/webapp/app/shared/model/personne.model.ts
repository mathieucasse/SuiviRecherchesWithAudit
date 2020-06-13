export interface IPersonne {
  id?: number;
  firstName?: string;
  lastName?: string;
  email?: string;
  phoneNumber?: string;
  entrepriseName?: string;
  entrepriseId?: number;
}

export class Personne implements IPersonne {
  constructor(
    public id?: number,
    public firstName?: string,
    public lastName?: string,
    public email?: string,
    public phoneNumber?: string,
    public entrepriseName?: string,
    public entrepriseId?: number
  ) {}
}
