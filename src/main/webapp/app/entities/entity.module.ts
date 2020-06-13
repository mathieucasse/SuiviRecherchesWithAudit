import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'entreprise',
        loadChildren: () => import('./entreprise/entreprise.module').then(m => m.SuiviRecherchesEntrepriseModule)
      },
      {
        path: 'personne',
        loadChildren: () => import('./personne/personne.module').then(m => m.SuiviRecherchesPersonneModule)
      },
      {
        path: 'recherche',
        loadChildren: () => import('./recherche/recherche.module').then(m => m.SuiviRecherchesRechercheModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class SuiviRecherchesEntityModule {}
