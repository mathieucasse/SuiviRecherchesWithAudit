import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { RechercheService } from 'app/entities/recherche/recherche.service';
import { IRecherche, Recherche } from 'app/shared/model/recherche.model';
import { OffreDeService } from 'app/shared/model/enumerations/offre-de-service.model';
import { ResOffreDeService } from 'app/shared/model/enumerations/res-offre-de-service.model';

describe('Service Tests', () => {
  describe('Recherche Service', () => {
    let injector: TestBed;
    let service: RechercheService;
    let httpMock: HttpTestingController;
    let elemDefault: IRecherche;
    let expectedResult: IRecherche | IRecherche[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(RechercheService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Recherche(
        0,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        0,
        OffreDeService.Email,
        ResOffreDeService.EnCours,
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Recherche', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            date: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate
          },
          returnedFromService
        );

        service.create(new Recherche()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Recherche', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_FORMAT),
            poste: 'BBBBBB',
            desciptif: 'BBBBBB',
            location: 'BBBBBB',
            assignationORP: true,
            txactivite: 1,
            offredeservice: 'BBBBBB',
            resoffredeservice: 'BBBBBB',
            motifres: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Recherche', () => {
        const returnedFromService = Object.assign(
          {
            date: currentDate.format(DATE_FORMAT),
            poste: 'BBBBBB',
            desciptif: 'BBBBBB',
            location: 'BBBBBB',
            assignationORP: true,
            txactivite: 1,
            offredeservice: 'BBBBBB',
            resoffredeservice: 'BBBBBB',
            motifres: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            date: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Recherche', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
