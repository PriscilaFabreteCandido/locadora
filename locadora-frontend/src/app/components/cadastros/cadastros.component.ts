import { ConsultasService } from '../../services/consultas.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService } from 'primeng/api';

@Component({
  selector: 'cadastros',
  templateUrl: './cadastros.component.html',
  styleUrls: ['./cadastros.component.scss']
})
export class CadastrosComponent implements OnInit{
  @Input() idCliente: number = 0;
  @Input() nomeEntidade: string = "";
  @Output() onSave = new EventEmitter<any>();

  cols: any[] = [];
  results: any[] = [];

  atributos: string[] = ['nome', 'teste'];
  openDialog: boolean = false;
  idEntidade: number = 0;
  rota: string = '';
  openDialogDependentes: boolean = false;
  pessoas = [
    {
      nome: "João Silva",
      endereco: "Rua A, 123",
      telefone: "(11) 1234-5678",
      sexo: "Masculino",
      CPF: "123.456.789-00",
      dataNascimento: "",
      ativo: false
    },
    {
      nome: "Maria Oliveira",
      endereco: "Avenida B, 456",
      telefone: "(22) 9876-5432",
      sexo: "Feminino",
      CPF: "987.654.321-00",
      dataNascimento: "",
      ativo: true
    }
  ];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private consultasService: ConsultasService,
              private confirmationService: ConfirmationService
    ) { }

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;

    if(this.nomeEntidade){
      this.rota = this.getRota(this.nomeEntidade);
    }else{
      this.route.params.subscribe(params => {
        this.nomeEntidade = params['tipo'];
        this.rota = this.getRota(this.nomeEntidade);
      });
    }

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
      case 'Cliente':
        rota = '/socios';
        break;
    }

    return rota;
  }

  getColsByTipoEnt(tipo: string): any[]{
    let cols: any = [];
    switch(tipo){
      case 'Ator':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text'  , isShow: true, isShowForm: true},
        ];
        break;
      case 'Diretor':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text' , isShow: true, isShowForm: true},
        ];
        break;
      case 'Classe':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text' , isShow: true, isShowForm: true},
          { field: 'valor', header: 'Valor', type: 'number'  , isShow: true, isShowForm: true},
          { field: 'prazoDevolucao', header: 'Prazo de Devolução', type: 'number'  , isShow: true, isShowForm: true},
        ];
        break;
      case 'Diretor':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text' , isShow: true, isShowForm: true},
        ];
        break;
      case 'Item':
        cols = [
          { field: 'numSerie', header: 'Número da Série', type: 'number', isShow: true, isShowForm: true},
          { field: 'titulo', header: 'Título', type: 'titulo', isShow: false, isShowForm: true},
          { field: 'dtAquisicao', header: 'Data de Aquisição', type: 'date'  , isShow: true, isShowForm: true},
          { field: 'tipoItem', header: 'Tipo Item', type: 'text', isShow: true, isShowForm: true},
        ];
        break;
      case 'Titulo':
        cols = [
          { field: 'nome', header: 'Nome', type: 'text', isShow: true, isShowForm: true},
          { field: 'categoria', header: 'Categoria', type: 'text', isShow: true, isShowForm: true},
          { field: 'sinopse', header: 'Sinopse', type: 'text', isShow: true, isShowForm: true},
          { field: 'ano', header: 'Ano', type: 'number', isShow: true, isShowForm: true},
          { field: 'atores', header: 'Atores', type: 'ator', isShow: false, isShowForm: true},
          { field: 'diretor', header: 'Diretor', type: 'diretor', isShow: false, isShowForm: true},
          { field: 'classe', header: 'Classe', type: 'classe', isShow: false, isShowForm: true},
        ];
        break;
      case 'Cliente':
        cols = [
          { field: 'numInscricao', header: 'Número de Inscrição', type: 'text', isShow: true, isShowForm: false},
          { field: 'nome', header: 'Nome', type: 'text', isShow: true, isShowForm: true},
          { field: 'dtNascimento', header: 'Data de Nascimento', type: 'date', isShow: false, isShowForm: true},
          { field: 'sexo', header: 'Sexo', type: 'sexo', isShow: false, isShowForm: true},
          { field: 'CPF', header: 'CPF', type: 'text', isShow: false, isShowForm: true},
          { field: 'endereco', header: 'Endereco', type: 'text', isShow: true, isShowForm: true},
          { field: 'telefone', header: 'Telefone', type: 'text', isShow: true, isShowForm: true},

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
    this.nomeEntidade == "Cliente" ? idValue = entity.numInscricao : '';
    console.log('entity', entity)
    this.rota = this.getRota(this.nomeEntidade);

    this.consultasService.delete(idValue, this.rota + '/excluir').subscribe(resp => {
      this.setResults(entity);
    });
  }

  setResults(entity: any){
    for (const key in entity) {
      if (key.startsWith('id')) {
        this.results = this.results.filter(x => x[key] != entity[key]);
      } else if(this.nomeEntidade == "Cliente" && key.startsWith('numInscricao')){
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

  gerenciarDependente(evnt: any){
    this.openDialogDependentes = true;
  }

  ativarOrDesativarCliente(data: any){

  }

  onSaveCadastros(event: any){
    console.log(event)
  }
}
