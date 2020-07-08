import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RechercheComponentsPage, RechercheDeleteDialog, RechercheUpdatePage } from './recherche.page-object';

const expect = chai.expect;

describe('Recherche e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let rechercheComponentsPage: RechercheComponentsPage;
  let rechercheUpdatePage: RechercheUpdatePage;
  let rechercheDeleteDialog: RechercheDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Recherches', async () => {
    await navBarPage.goToEntity('recherche');
    rechercheComponentsPage = new RechercheComponentsPage();
    await browser.wait(ec.visibilityOf(rechercheComponentsPage.title), 5000);
    expect(await rechercheComponentsPage.getTitle()).to.eq('suiviRecherchesApp.recherche.home.title');
    await browser.wait(ec.or(ec.visibilityOf(rechercheComponentsPage.entities), ec.visibilityOf(rechercheComponentsPage.noResult)), 1000);
  });

  it('should load create Recherche page', async () => {
    await rechercheComponentsPage.clickOnCreateButton();
    rechercheUpdatePage = new RechercheUpdatePage();
    expect(await rechercheUpdatePage.getPageTitle()).to.eq('suiviRecherchesApp.recherche.home.createOrEditLabel');
    await rechercheUpdatePage.cancel();
  });

  it('should create and save Recherches', async () => {
    const nbButtonsBeforeCreate = await rechercheComponentsPage.countDeleteButtons();

    await rechercheComponentsPage.clickOnCreateButton();

    await promise.all([
      rechercheUpdatePage.setDateInput('2000-12-31'),
      rechercheUpdatePage.setPosteInput('poste'),
      rechercheUpdatePage.setDesciptifInput('desciptif'),
      rechercheUpdatePage.setLocationInput('location'),
      rechercheUpdatePage.setTxactiviteInput('5'),
      rechercheUpdatePage.offredeserviceSelectLastOption(),
      rechercheUpdatePage.resoffredeserviceSelectLastOption(),
      rechercheUpdatePage.setMotifresInput('motifres'),
      rechercheUpdatePage.userSelectLastOption(),
      rechercheUpdatePage.entPrestataireSelectLastOption(),
      rechercheUpdatePage.entFinaleSelectLastOption(),
      rechercheUpdatePage.contactSelectLastOption()
    ]);

    expect(await rechercheUpdatePage.getDateInput()).to.eq('2000-12-31', 'Expected date value to be equals to 2000-12-31');
    expect(await rechercheUpdatePage.getPosteInput()).to.eq('poste', 'Expected Poste value to be equals to poste');
    expect(await rechercheUpdatePage.getDesciptifInput()).to.eq('desciptif', 'Expected Desciptif value to be equals to desciptif');
    expect(await rechercheUpdatePage.getLocationInput()).to.eq('location', 'Expected Location value to be equals to location');
    const selectedAssignationORP = rechercheUpdatePage.getAssignationORPInput();
    if (await selectedAssignationORP.isSelected()) {
      await rechercheUpdatePage.getAssignationORPInput().click();
      expect(await rechercheUpdatePage.getAssignationORPInput().isSelected(), 'Expected assignationORP not to be selected').to.be.false;
    } else {
      await rechercheUpdatePage.getAssignationORPInput().click();
      expect(await rechercheUpdatePage.getAssignationORPInput().isSelected(), 'Expected assignationORP to be selected').to.be.true;
    }
    expect(await rechercheUpdatePage.getTxactiviteInput()).to.eq('5', 'Expected txactivite value to be equals to 5');
    expect(await rechercheUpdatePage.getMotifresInput()).to.eq('motifres', 'Expected Motifres value to be equals to motifres');

    await rechercheUpdatePage.save();
    expect(await rechercheUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await rechercheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Recherche', async () => {
    const nbButtonsBeforeDelete = await rechercheComponentsPage.countDeleteButtons();
    await rechercheComponentsPage.clickOnLastDeleteButton();

    rechercheDeleteDialog = new RechercheDeleteDialog();
    expect(await rechercheDeleteDialog.getDialogTitle()).to.eq('suiviRecherchesApp.recherche.delete.question');
    await rechercheDeleteDialog.clickOnConfirmButton();

    expect(await rechercheComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
