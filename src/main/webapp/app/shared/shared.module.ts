import { NgModule } from '@angular/core';
import { SuiviRecherchesSharedLibsModule } from './shared-libs.module';
import { FindLanguageFromKeyPipe } from './language/find-language-from-key.pipe';
import { AlertComponent } from './alert/alert.component';
import { AlertErrorComponent } from './alert/alert-error.component';
import { LoginModalComponent } from './login/login.component';
import { HasAnyAuthorityDirective } from './auth/has-any-authority.directive';
import { LocalizedDatePipe } from 'app/shared/language/localizedDatePipe';

@NgModule({
  imports: [SuiviRecherchesSharedLibsModule],
  declarations: [
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    LocalizedDatePipe
  ],
  entryComponents: [LoginModalComponent],
  exports: [
    SuiviRecherchesSharedLibsModule,
    FindLanguageFromKeyPipe,
    AlertComponent,
    AlertErrorComponent,
    LoginModalComponent,
    HasAnyAuthorityDirective,
    LocalizedDatePipe
  ]
})
export class SuiviRecherchesSharedModule {}
