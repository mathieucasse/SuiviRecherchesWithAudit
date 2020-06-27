import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntreprise } from 'app/shared/model/entreprise.model';
import { EntrepriseService } from './entreprise.service';

@Component({
  templateUrl: './entreprise-update-dialog.component.html'
})
export class EntrepriseUpdateDialogComponent {
  entreprise?: IEntreprise;

  constructor(
    protected entrepriseService: EntrepriseService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  edit(): void {
    this.activeModal.dismiss();
  }
}
