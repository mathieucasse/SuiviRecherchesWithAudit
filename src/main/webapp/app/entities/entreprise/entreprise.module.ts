import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SuiviRecherchesSharedModule } from 'app/shared/shared.module';
import { EntrepriseComponent } from './entreprise.component';
import { EntrepriseDetailComponent } from './entreprise-detail.component';
import { EntrepriseUpdateComponent } from './entreprise-update.component';
import { EntrepriseDeleteDialogComponent } from './entreprise-delete-dialog.component';
import { EntrepriseUpdateDialogComponent } from './entreprise-update-dialog.component';
import { EntrepriseDetailDialogComponent } from './entreprise-detail-dialog.component';
import { entrepriseRoute } from './entreprise.route';

@NgModule({
  imports: [SuiviRecherchesSharedModule, RouterModule.forChild(entrepriseRoute)],
  declarations: [
    EntrepriseComponent,
    EntrepriseDetailComponent,
    EntrepriseUpdateComponent,
    EntrepriseDeleteDialogComponent,
    EntrepriseUpdateDialogComponent,
    EntrepriseDetailDialogComponent
  ],
  entryComponents: [EntrepriseDeleteDialogComponent, EntrepriseUpdateDialogComponent, EntrepriseDetailDialogComponent]
})
export class SuiviRecherchesEntrepriseModule {}
