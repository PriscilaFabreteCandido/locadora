import { ConsultasService } from 'src/app/services/consultas.service';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';

@Component({
  selector: 'app-efetuar-devolucao',
  templateUrl: './efetuar-devolucao.component.html',
  styleUrls: ['./efetuar-devolucao.component.scss']
})
export class EfetuarDevolucaoComponent implements OnInit{
  @ViewChild('numSerieInput') numSerieInput: ElementRef | undefined;
  numSerie: number | undefined;
  multa: number = 0.0;
  item = 'Cliente: Alberto, Item: Fita 1, Data prevista de devolução: 25/12/2023';

  constructor(private consultasService: ConsultasService){}

  ngOnInit(): void {

  }

  getLocacao(){

  }

  updateNumSerie(): void {
    this.numSerie = this.numSerieInput?.nativeElement.value;
  }

  getItem(){
    if(this.numSerie){
      this.consultasService.getById(this.numSerie, 'locacoes/getById').subscribe(resp =>{

      })
    }

  }
}
