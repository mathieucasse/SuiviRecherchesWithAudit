import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PersonneComponentsPage, PersonneDeleteDialog, PersonneUpdatePage } from './personne.page-object';

const expect = chai.expect;

describe('Personne e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let personneComponentsPage: PersonneComponentsPage;
  let personneUpdatePage: PersonneUpdatePage;
  let personneDeleteDialog: PersonneDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Personnes', async () => {
    await navBarPage.goToEntity('personne');
    personneComponentsPage = new PersonneComponentsPage();
    await browser.wait(ec.visibilityOf(personneComponentsPage.title), 5000);
    expect(await personneComponentsPage.getTitle()).to.eq('suiviRecherchesApp.personne.home.title');
    await browser.wait(ec.or(ec.visibilityOf(personneComponentsPage.entities), ec.visibilityOf(personneComponentsPage.noResult)), 1000);
  });

  it('should load create Personne page', async () => {
    await personneComponentsPage.clickOnCreateButton();
    personneUpdatePage = new PersonneUpdatePage();
    expect(await personneUpdatePage.getPageTitle()).to.eq('suiviRecherchesApp.personne.home.createOrEditLabel');
    await personneUpdatePage.cancel();
  });

  it('should create and save Personnes', async () => {
    const nbButtonsBeforeCreate = await personneComponentsPage.countDeleteButtons();

    await personneComponentsPage.clickOnCreateButton();

    await promise.all([
      personneUpdatePage.setFirstNameInput('firstName'),
      personneUpdatePage.setLastNameInput('lastName'),
      personneUpdatePage.setEmailInput('email'),
      personneUpdatePage.setPhoneNumberInput('phoneNumber'),
      personneUpdatePage.entrepriseSelectLastOption()
    ]);

    expect(await personneUpdatePage.getFirstNameInput()).to.eq('firstName', 'Expected FirstName value to be equals to firstName');
    expect(await personneUpdatePage.getLastNameInput()).to.eq('lastName', 'Expected LastName value to be equals to lastName');
    expect(await personneUpdatePage.getEmailInput()).to.eq('email', 'Expected Email value to be equals to email');
    expect(await personneUpdatePage.getPhoneNumberInput()).to.eq('phoneNumber', 'Expected PhoneNumber value to be equals to phoneNumber');

    await personneUpdatePage.save();
    expect(await personneUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await personneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Personne', async () => {
    const nbButtonsBeforeDelete = await personneComponentsPage.countDeleteButtons();
    await personneComponentsPage.clickOnLastDeleteButton();

    personneDeleteDialog = new PersonneDeleteDialog();
    expect(await personneDeleteDialog.getDialogTitle()).to.eq('suiviRecherchesApp.personne.delete.question');
    await personneDeleteDialog.clickOnConfirmButton();

    expect(await personneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
