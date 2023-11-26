import { ConsultasService } from 'src/app/services/consultas.service';
import { Component, ElementRef, Input, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'efetuar-devolucao',
  templateUrl: './efetuar-devolucao.component.html',
  styleUrls: ['./efetuar-devolucao.component.scss']
})
export class EfetuarDevolucaoComponent implements OnInit{
  @Input('numSerie') numSerie: number = 0;
  @Output('onSave') onSave = new EventEmitter<any>();

  multa: number = 0.0;
  total: number = 0.0;
  locacao: any;

  constructor(private consultasService: ConsultasService){}

  ngOnInit(): void {
    this.getItem();
  }

  getLocacao(){

  }

  updateNumSerie(): void {
    // this.numSerie = this.numSerieInput?.nativeElement.value;
  }

  getItem(){
    if(this.numSerie){
      this.consultasService.getById(this.numSerie, '/locacoes').subscribe(resp =>{
        this.locacao = resp;
        this.getMulta();
      })
    }

  }

  devolver(){
    this.consultasService.devolverItem(this.numSerie).subscribe(resp => {
      this.onSave.emit(true);
    })
  }

  getMulta(){
    this.consultasService.obterMulta(this.locacao.id_locacao).subscribe(resp => {
      this.multa = resp;
      this.total = this.multa + this.locacao.valorCobrado;
    });
  }


}
