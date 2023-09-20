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
    //etste
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
    let item;
    if(this.results && this.results.length > 0){
      item = this.results.filter(x => event.id == event.id);
    }

    if(!item){

    }

    this.results.push(event);
    this.openDialog = false;
    this.idEntidade = 0;
  }

  deletarEntidade(entity: any){
    var idValue = this.getIdEntidade(entity);
    this.consultasService.delete(idValue, this.rota + '/excluir').subscribe(resp => {
      this.setResults(entity);
    });
  }

  setResults(entity: any){
    for (const key in entity) {
      if (key.startsWith('id')) {
        this.results = this.results.filter(x => x[key] != entity[key]);
      }
    }
  }

  getIdEntidade(entity: any): any{
    for (const key in entity) {
      if (key.startsWith('id')) {
        const idValue = entity[key];
        return idValue;
      }
    }

    return null;
  }

  editarEntidade(entity: any){
    this.idEntidade = this.getIdEntidade(entity);
    this.openDialog = true;
  }

  openModal(){
    this.openDialog = true;
    this.idEntidade = 0;
  }
}
