import { Component, Input, OnInit } from '@angular/core';
import { ConsultasService } from 'src/app/services/consultas.service';

@Component({
  selector: 'show-generic',
  templateUrl: './show-generic.component.html',
  styleUrls: ['./show-generic.component.scss']
})
export class ShowGenericComponent implements OnInit {
  @Input() numSerie: number = 0;
  @Input() titulo: any;
  @Input() cliente: any;

  constructor(private consultasService: ConsultasService){}

  ngOnInit(){
    console.log('numSerie', this.numSerie)
    this.getItem();
  }

  getItem(){
    if(this.numSerie){
      this.consultasService.getById(this.numSerie, '/socios').subscribe(resp =>{
        this.cliente = resp;
      })
    }

  }

  getStringAtores(atores: any[]): string{
    if(atores){
      let string = '';
      atores.forEach(x => {
        string += x.nome + "; "
      })
      return string;
    }
    return '';
  }

  
}
