import { Moment } from 'moment';
import { OffreDeService } from 'app/shared/model/enumerations/offre-de-service.model';
import { ResOffreDeService } from 'app/shared/model/enumerations/res-offre-de-service.model';

export interface IRecherche {
  id?: number;
  date?: Moment;
  poste?: string;
  location?: string;
  assignationORP?: boolean;
  txactivite?: number;
  offredeservice?: OffreDeService;
  resoffredeservice?: ResOffreDeService;
  motifres?: string;
  entPrestataireName?: string;
  entPrestataireId?: number;
  entFinaleName?: string;
  entFinaleId?: number;
  contactLastName?: string;
  contactId?: number;
}

export class Recherche implements IRecherche {
  constructor(
    public id?: number,
    public date?: Moment,
    public poste?: string,
    public location?: string,
    public assignationORP?: boolean,
    public txactivite?: number,
    public offredeservice?: OffreDeService,
    public resoffredeservice?: ResOffreDeService,
    public motifres?: string,
    public entPrestataireName?: string,
    public entPrestataireId?: number,
    public entFinaleName?: string,
    public entFinaleId?: number,
    public contactLastName?: string,
    public contactId?: number
  ) {
    this.assignationORP = this.assignationORP || false;
  }
}
