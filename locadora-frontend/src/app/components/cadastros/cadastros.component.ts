import { ConsultasService } from '../../services/consultas.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'cadastros',
  templateUrl: './cadastros.component.html',
  styleUrls: ['./cadastros.component.scss']
})
export class CadastrosComponent implements OnInit{

  cols: any[] = [];
  results: any[] = [];
  nomeEntidade: string = "";
  atributos: string[] = ['nome', 'teste'];
  openDialog: boolean = false;
  idEntidade: number = 0;
  rota: string = '';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private consultasService: ConsultasService
    ) { }

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.route.params.subscribe(params => {
      this.nomeEntidade = params['tipo'];
      this.rota = this.getRota(this.nomeEntidade);
    });

    this.cols = this.getColsByTipoEnt(this.nomeEntidade);
    this.consultasService.getAll(this.rota).subscribe(resp => {
      if(resp){
        this.results = resp;
      }
    });
  }

  getRota(tipo: string): string{
    let rota = '';
    switch(tipo){
      case 'Ator':
        rota = '/atores'
        break;
      case 'Diretor':
        rota = '/diretores'
        break;
      case 'Classe':
        rota = '/classes'
        break;
    }

    return rota;
  }

  getColsByTipoEnt(tipo: string): any[]{
    let cols: any = [];
    switch(tipo){
      case 'Ator':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text' },
        ];
        break;
      case 'Diretor':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text'},
        ];
        break;
      case 'Classe':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text'},
          { field: 'valor', header: 'Valor', type: 'number' },
          { field: 'prazoDevolucao', header: 'Prazo de Devolução', type: 'date' },
        ];
        break;
    }

    return cols;
  }

  processarFormulario(event: any){
    let item
    if(this.results && this.results.length){
      item = this.results.filter(x => event.id == event.id);
    }
    if(!item){

    }
    this.results.push(event);
    this.openDialog = false;
  }

  deletarEntidade(entity: any){
    for (const key in entity) {
      if (key.startsWith('id')) {
        const idValue = entity[key];
        this.consultasService.delete(idValue, this.rota).subscribe(resp => {
          this.results = this.results.filter(x => x.id != entity.id);

        });
      }
    }



  }

  editarEntidade(entity: any){
    this.idEntidade = entity.id;
    this.openDialog = true;
  }
}
