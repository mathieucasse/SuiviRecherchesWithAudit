import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRecherche } from 'app/shared/model/recherche.model';

type EntityResponseType = HttpResponse<IRecherche>;
type EntityArrayResponseType = HttpResponse<IRecherche[]>;

@Injectable({ providedIn: 'root' })
export class RechercheService {
  public resourceUrl = SERVER_API_URL + 'api/recherches';

  constructor(protected http: HttpClient) {}

  create(recherche: IRecherche): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recherche);
    return this.http
      .post<IRecherche>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(recherche: IRecherche): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(recherche);
    return this.http
      .put<IRecherche>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRecherche>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRecherche[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(recherche: IRecherche): IRecherche {
    const copy: IRecherche = Object.assign({}, recherche, {
      date: recherche.date && recherche.date.isValid() ? recherche.date.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.date = res.body.date ? moment(res.body.date) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((recherche: IRecherche) => {
        recherche.date = recherche.date ? moment(recherche.date) : undefined;
      });
    }
    return res;
  }
}
