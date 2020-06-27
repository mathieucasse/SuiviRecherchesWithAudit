import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEntreprise } from 'app/shared/model/entreprise.model';

@Component({
  templateUrl: './entreprise-detail-dialog.component.html'
})
export class EntrepriseDetailDialogComponent {
  entreprise: IEntreprise | null = null;

  constructor(public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  edit(): void {
    this.activeModal.dismiss();
  }
}
