import { by, element, ElementFinder } from 'protractor';

export class RechercheComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-recherche div table .btn-danger'));
  title = element.all(by.css('jhi-recherche div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class RechercheUpdatePage {
  pageTitle = element(by.id('jhi-recherche-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dateInput = element(by.id('field_date'));
  posteInput = element(by.id('field_poste'));
  desciptifInput = element(by.id('field_desciptif'));
  locationInput = element(by.id('field_location'));
  assignationORPInput = element(by.id('field_assignationORP'));
  txactiviteInput = element(by.id('field_txactivite'));
  offredeserviceSelect = element(by.id('field_offredeservice'));
  resoffredeserviceSelect = element(by.id('field_resoffredeservice'));
  motifresInput = element(by.id('field_motifres'));

  userSelect = element(by.id('field_user'));
  entPrestataireSelect = element(by.id('field_entPrestataire'));
  entFinaleSelect = element(by.id('field_entFinale'));
  contactSelect = element(by.id('field_contact'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDateInput(date: string): Promise<void> {
    await this.dateInput.sendKeys(date);
  }

  async getDateInput(): Promise<string> {
    return await this.dateInput.getAttribute('value');
  }

  async setPosteInput(poste: string): Promise<void> {
    await this.posteInput.sendKeys(poste);
  }

  async getPosteInput(): Promise<string> {
    return await this.posteInput.getAttribute('value');
  }

  async setDesciptifInput(desciptif: string): Promise<void> {
    await this.desciptifInput.sendKeys(desciptif);
  }

  async getDesciptifInput(): Promise<string> {
    return await this.desciptifInput.getAttribute('value');
  }

  async setLocationInput(location: string): Promise<void> {
    await this.locationInput.sendKeys(location);
  }

  async getLocationInput(): Promise<string> {
    return await this.locationInput.getAttribute('value');
  }

  getAssignationORPInput(): ElementFinder {
    return this.assignationORPInput;
  }

  async setTxactiviteInput(txactivite: string): Promise<void> {
    await this.txactiviteInput.sendKeys(txactivite);
  }

  async getTxactiviteInput(): Promise<string> {
    return await this.txactiviteInput.getAttribute('value');
  }

  async setOffredeserviceSelect(offredeservice: string): Promise<void> {
    await this.offredeserviceSelect.sendKeys(offredeservice);
  }

  async getOffredeserviceSelect(): Promise<string> {
    return await this.offredeserviceSelect.element(by.css('option:checked')).getText();
  }

  async offredeserviceSelectLastOption(): Promise<void> {
    await this.offredeserviceSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setResoffredeserviceSelect(resoffredeservice: string): Promise<void> {
    await this.resoffredeserviceSelect.sendKeys(resoffredeservice);
  }

  async getResoffredeserviceSelect(): Promise<string> {
    return await this.resoffredeserviceSelect.element(by.css('option:checked')).getText();
  }

  async resoffredeserviceSelectLastOption(): Promise<void> {
    await this.resoffredeserviceSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setMotifresInput(motifres: string): Promise<void> {
    await this.motifresInput.sendKeys(motifres);
  }

  async getMotifresInput(): Promise<string> {
    return await this.motifresInput.getAttribute('value');
  }

  async userSelectLastOption(): Promise<void> {
    await this.userSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async userSelectOption(option: string): Promise<void> {
    await this.userSelect.sendKeys(option);
  }

  getUserSelect(): ElementFinder {
    return this.userSelect;
  }

  async getUserSelectedOption(): Promise<string> {
    return await this.userSelect.element(by.css('option:checked')).getText();
  }

  async entPrestataireSelectLastOption(): Promise<void> {
    await this.entPrestataireSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async entPrestataireSelectOption(option: string): Promise<void> {
    await this.entPrestataireSelect.sendKeys(option);
  }

  getEntPrestataireSelect(): ElementFinder {
    return this.entPrestataireSelect;
  }

  async getEntPrestataireSelectedOption(): Promise<string> {
    return await this.entPrestataireSelect.element(by.css('option:checked')).getText();
  }

  async entFinaleSelectLastOption(): Promise<void> {
    await this.entFinaleSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async entFinaleSelectOption(option: string): Promise<void> {
    await this.entFinaleSelect.sendKeys(option);
  }

  getEntFinaleSelect(): ElementFinder {
    return this.entFinaleSelect;
  }

  async getEntFinaleSelectedOption(): Promise<string> {
    return await this.entFinaleSelect.element(by.css('option:checked')).getText();
  }

  async contactSelectLastOption(): Promise<void> {
    await this.contactSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async contactSelectOption(option: string): Promise<void> {
    await this.contactSelect.sendKeys(option);
  }

  getContactSelect(): ElementFinder {
    return this.contactSelect;
  }

  async getContactSelectedOption(): Promise<string> {
    return await this.contactSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class RechercheDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-recherche-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-recherche'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
