import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-efetuar-devolucao',
  templateUrl: './efetuar-devolucao.component.html',
  styleUrls: ['./efetuar-devolucao.component.scss']
})
export class EfetuarDevolucaoComponent implements OnInit{

  numSerie: any = '';
  multa: number = 0.0;
  item = 'Cliente: Alberto, Item: Fita 1, Data prevista de devolução: 25/12/2023'
  ngOnInit(): void {

  }

  getLocacao(){

  }
}
