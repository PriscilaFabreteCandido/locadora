import { Injectable, Input } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConsultasServiceService {
  @Input() atributos: string[] = [];
  constructor() {

  }
}
