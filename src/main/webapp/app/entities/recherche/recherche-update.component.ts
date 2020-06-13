import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IRecherche, Recherche } from 'app/shared/model/recherche.model';
import { RechercheService } from './recherche.service';
import { IEntreprise } from 'app/shared/model/entreprise.model';
import { EntrepriseService } from 'app/entities/entreprise/entreprise.service';
import { IPersonne } from 'app/shared/model/personne.model';
import { PersonneService } from 'app/entities/personne/personne.service';

type SelectableEntity = IEntreprise | IPersonne;

@Component({
  selector: 'jhi-recherche-update',
  templateUrl: './recherche-update.component.html'
})
export class RechercheUpdateComponent implements OnInit {
  isSaving = false;
  entreprises: IEntreprise[] = [];
  personnes: IPersonne[] = [];

  editForm = this.fb.group({
    id: [],
    date: [],
    poste: [],
    location: [],
    assignationORP: [],
    txactivite: [null, [Validators.min(1), Validators.max(100)]],
    offredeservice: [],
    resoffredeservice: [],
    motifres: [],
    entPrestataireId: [],
    entFinaleId: [],
    contactId: []
  });

  constructor(
    protected rechercheService: RechercheService,
    protected entrepriseService: EntrepriseService,
    protected personneService: PersonneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ recherche }) => {
      if (!recherche.id) {
        const today = moment().startOf('day');
        recherche.date = today;
      }

      this.updateForm(recherche);

      this.entrepriseService.query().subscribe((res: HttpResponse<IEntreprise[]>) => (this.entreprises = res.body || []));

      this.personneService.query().subscribe((res: HttpResponse<IPersonne[]>) => (this.personnes = res.body || []));
    });
  }

  updateForm(recherche: IRecherche): void {
    this.editForm.patchValue({
      id: recherche.id,
      date: recherche.date ? recherche.date.format(DATE_TIME_FORMAT) : null,
      poste: recherche.poste,
      location: recherche.location,
      assignationORP: recherche.assignationORP,
      txactivite: recherche.txactivite,
      offredeservice: recherche.offredeservice,
      resoffredeservice: recherche.resoffredeservice,
      motifres: recherche.motifres,
      entPrestataireId: recherche.entPrestataireId,
      entFinaleId: recherche.entFinaleId,
      contactId: recherche.contactId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const recherche = this.createFromForm();
    if (recherche.id !== undefined) {
      this.subscribeToSaveResponse(this.rechercheService.update(recherche));
    } else {
      this.subscribeToSaveResponse(this.rechercheService.create(recherche));
    }
  }

  private createFromForm(): IRecherche {
    return {
      ...new Recherche(),
      id: this.editForm.get(['id'])!.value,
      date: this.editForm.get(['date'])!.value ? moment(this.editForm.get(['date'])!.value, DATE_TIME_FORMAT) : undefined,
      poste: this.editForm.get(['poste'])!.value,
      location: this.editForm.get(['location'])!.value,
      assignationORP: this.editForm.get(['assignationORP'])!.value,
      txactivite: this.editForm.get(['txactivite'])!.value,
      offredeservice: this.editForm.get(['offredeservice'])!.value,
      resoffredeservice: this.editForm.get(['resoffredeservice'])!.value,
      motifres: this.editForm.get(['motifres'])!.value,
      entPrestataireId: this.editForm.get(['entPrestataireId'])!.value,
      entFinaleId: this.editForm.get(['entFinaleId'])!.value,
      contactId: this.editForm.get(['contactId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRecherche>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
