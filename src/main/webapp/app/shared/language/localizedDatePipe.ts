import { DatePipe } from '@angular/common';
import { Pipe, PipeTransform } from '@angular/core';
import { JhiLanguageService } from 'ng-jhipster';

@Pipe({ name: 'localizedDate' })
export class LocalizedDatePipe implements PipeTransform {
  constructor(private languageService: JhiLanguageService) {}

  transform(value: any, pattern = 'mediumDate'): any {
    const datePipe: DatePipe = new DatePipe(this.languageService.getCurrentLanguage());
    return datePipe.transform(value, pattern);
  }
}
