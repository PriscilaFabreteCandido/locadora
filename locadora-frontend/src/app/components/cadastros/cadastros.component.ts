import { ConsultasService } from '../../services/consultas.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService } from 'primeng/api';

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
              private consultasService: ConsultasService,
              private confirmationService: ConfirmationService
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
        rota = '/classes';
        break;
      case 'Item':
          rota = '/itens';
          break;
      case 'Titulo':
          rota = '/titulos';
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
          { field: 'prazoDevolucao', header: 'Prazo de Devolução', type: 'number' },
        ];
        break;
      case 'Diretor':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text'},
        ];
        break;
      case 'Item':
        cols = [
          { field: 'numSerie', header: 'Número da Série', type: 'number'},
          { field: 'dtAquisicao', header: 'Data de Aquisição', type: 'date' },
          { field: 'tipoItem', header: 'Tipo Item', type: 'text' },
        ];
        break;
      case 'Titulo':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text'},
          { field: 'categoria', header: 'Categoria', type: 'text' },
          { field: 'sinopse', header: 'Sinopse', type: 'text' },
          { field: 'ano', header: 'Ano', type: 'number' },
          { field: 'atores', header: 'Atores', type: 'ator' },
          { field: 'diretor', header: 'Diretor', type: 'diretor' },
          { field: 'classe', header: 'Classe', type: 'classe' },
          { field: 'itens', header: 'Itens', type: 'item' },
        ];
        break;
    }

    return cols;
  }

  setEntidade(entity: any, newEntity: any) {
    for (const key in entity) {
      if (newEntity.hasOwnProperty(key)) {
        entity[key] = newEntity[key];
      }
    }
  }

  processarFormulario(event: any){
    let item;
    if(this.results && this.results.length > 0){
      for (const key in event) {
        if (key.startsWith('id')) {
          item = this.results.filter(x => x[key] == event[key])[0];
          // const index = this.results.indexOf(item);
        }
      }
    }

    if(!item){
      this.results.push(event);
    }else{
      this.setEntidade(item, event);
    }

    this.openDialog = false;
    this.idEntidade = 0;
  }

  showConfirmationToDelete(entity: any): void {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja excluir esta entidade?',
      accept: () => {
        // O código que será executado quando o usuário confirmar a exclusão
        this.deletarEntidade(entity);
      },
      reject: () => {
        // O código que será executado quando o usuário cancelar a exclusão (opcional)
      }
    });
  }

  deletarEntidade(entity: any){
    var idValue = this.getIdEntidade(entity);
    this.rota = this.getRota(this.nomeEntidade);

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
    this.rota = this.getRota(this.nomeEntidade);
    this.openDialog = true;
  }

  openModal(){
    this.rota = this.getRota(this.nomeEntidade) + '/adicionar';
    this.idEntidade = 0;
    this.openDialog = true;
  }


}
