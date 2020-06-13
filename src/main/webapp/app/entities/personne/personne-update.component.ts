import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPersonne, Personne } from 'app/shared/model/personne.model';
import { PersonneService } from './personne.service';
import { IEntreprise } from 'app/shared/model/entreprise.model';
import { EntrepriseService } from 'app/entities/entreprise/entreprise.service';

@Component({
  selector: 'jhi-personne-update',
  templateUrl: './personne-update.component.html'
})
export class PersonneUpdateComponent implements OnInit {
  isSaving = false;
  entreprises: IEntreprise[] = [];

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
    email: [],
    phoneNumber: [],
    entrepriseId: []
  });

  constructor(
    protected personneService: PersonneService,
    protected entrepriseService: EntrepriseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personne }) => {
      this.updateForm(personne);

      this.entrepriseService.query().subscribe((res: HttpResponse<IEntreprise[]>) => (this.entreprises = res.body || []));
    });
  }

  updateForm(personne: IPersonne): void {
    this.editForm.patchValue({
      id: personne.id,
      firstName: personne.firstName,
      lastName: personne.lastName,
      email: personne.email,
      phoneNumber: personne.phoneNumber,
      entrepriseId: personne.entrepriseId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const personne = this.createFromForm();
    if (personne.id !== undefined) {
      this.subscribeToSaveResponse(this.personneService.update(personne));
    } else {
      this.subscribeToSaveResponse(this.personneService.create(personne));
    }
  }

  private createFromForm(): IPersonne {
    return {
      ...new Personne(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      email: this.editForm.get(['email'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value,
      entrepriseId: this.editForm.get(['entrepriseId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonne>>): void {
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

  trackById(index: number, item: IEntreprise): any {
    return item.id;
  }
}
