import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EntrepriseComponentsPage, EntrepriseDeleteDialog, EntrepriseUpdatePage } from './entreprise.page-object';

const expect = chai.expect;

describe('Entreprise e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let entrepriseComponentsPage: EntrepriseComponentsPage;
  let entrepriseUpdatePage: EntrepriseUpdatePage;
  let entrepriseDeleteDialog: EntrepriseDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Entreprises', async () => {
    await navBarPage.goToEntity('entreprise');
    entrepriseComponentsPage = new EntrepriseComponentsPage();
    await browser.wait(ec.visibilityOf(entrepriseComponentsPage.title), 5000);
    expect(await entrepriseComponentsPage.getTitle()).to.eq('suiviRecherchesApp.entreprise.home.title');
    await browser.wait(ec.or(ec.visibilityOf(entrepriseComponentsPage.entities), ec.visibilityOf(entrepriseComponentsPage.noResult)), 1000);
  });

  it('should load create Entreprise page', async () => {
    await entrepriseComponentsPage.clickOnCreateButton();
    entrepriseUpdatePage = new EntrepriseUpdatePage();
    expect(await entrepriseUpdatePage.getPageTitle()).to.eq('suiviRecherchesApp.entreprise.home.createOrEditLabel');
    await entrepriseUpdatePage.cancel();
  });

  it('should create and save Entreprises', async () => {
    const nbButtonsBeforeCreate = await entrepriseComponentsPage.countDeleteButtons();

    await entrepriseComponentsPage.clickOnCreateButton();

    await promise.all([
      entrepriseUpdatePage.setNameInput('name'),
      entrepriseUpdatePage.setEmailInput('email'),
      entrepriseUpdatePage.setPhoneNumberInput('phoneNumber')
    ]);

    expect(await entrepriseUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await entrepriseUpdatePage.getEmailInput()).to.eq('email', 'Expected Email value to be equals to email');
    expect(await entrepriseUpdatePage.getPhoneNumberInput()).to.eq('phoneNumber', 'Expected PhoneNumber value to be equals to phoneNumber');

    await entrepriseUpdatePage.save();
    expect(await entrepriseUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await entrepriseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Entreprise', async () => {
    const nbButtonsBeforeDelete = await entrepriseComponentsPage.countDeleteButtons();
    await entrepriseComponentsPage.clickOnLastDeleteButton();

    entrepriseDeleteDialog = new EntrepriseDeleteDialog();
    expect(await entrepriseDeleteDialog.getDialogTitle()).to.eq('suiviRecherchesApp.entreprise.delete.question');
    await entrepriseDeleteDialog.clickOnConfirmButton();

    expect(await entrepriseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
