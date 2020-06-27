import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { JhiDataUtils, JhiFileLoadError, JhiEventManager, JhiEventWithContent } from 'ng-jhipster';

import { IRecherche, Recherche } from 'app/shared/model/recherche.model';
import { RechercheService } from './recherche.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
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
  dateDp: any;

  editForm = this.fb.group({
    id: [],
    date: [],
    poste: [],
    desciptif: [],
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
    protected dataUtils: JhiDataUtils,
    protected eventManager: JhiEventManager,
    protected rechercheService: RechercheService,
    protected entrepriseService: EntrepriseService,
    protected personneService: PersonneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ recherche }) => {
      this.updateForm(recherche);

      this.entrepriseService.query().subscribe((res: HttpResponse<IEntreprise[]>) => (this.entreprises = res.body || []));

      this.personneService.query().subscribe((res: HttpResponse<IPersonne[]>) => (this.personnes = res.body || []));
    });
  }

  updateForm(recherche: IRecherche): void {
    this.editForm.patchValue({
      id: recherche.id,
      date: recherche.date,
      poste: recherche.poste,
      desciptif: recherche.desciptif,
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

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(contentType: string, base64String: string): void {
    this.dataUtils.openFile(contentType, base64String);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe(null, (err: JhiFileLoadError) => {
      this.eventManager.broadcast(
        new JhiEventWithContent<AlertError>('suiviRecherchesApp.error', { ...err, key: 'error.file.' + err.key })
      );
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
      date: this.editForm.get(['date'])!.value,
      poste: this.editForm.get(['poste'])!.value,
      desciptif: this.editForm.get(['desciptif'])!.value,
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
