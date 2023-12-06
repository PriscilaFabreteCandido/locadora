import { ConsultasService } from '../../services/consultas.service';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';

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
  openDialogEfetuarDevolucao: boolean = false;
  openVisualizarLocacao: boolean = false;
  showBtnLeft = false;
  idSocio: number = 0;
  idLocacaoParaEditar: number = 0;
  clienteParaVisualizar: any;
  categorias: string[] = [
    'Ação',
    'Comédia',
    'Drama',
    'Ficção Científica',
    'Romance',
    'Terror',
    'Documentário',
    'Todos'
  ];

  categoriaFilter: any;
  nomeTituloFilter: any;
  atorFilter: any;
  atores: any = [];
  tituloParaVisualizar: any;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private consultasService: ConsultasService,
              private confirmationService: ConfirmationService,
              private messageService: MessageService,
    ) { }

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;

    if(this.nomeEntidade){
      this.rota = this.getRota(this.nomeEntidade);
    }else{
      this.route.params.subscribe(params => {
        this.nomeEntidade = params['tipo'];
        if(params['idSocio'] && params['showBtnLeft']){
          this.showBtnLeft = params['showBtnLeft'];
          this.idSocio = params['idSocio'];
        }
        this.rota = this.getRota(this.nomeEntidade);
      });
    }

    this.cols = this.getColsByTipoEnt(this.nomeEntidade);
    if(this.nomeEntidade == 'Dependente'){
      console.log('veio aqui')
      this.consultasService.getById(this.idSocio, '/socios').subscribe(resp => {
        if(resp){
          this.results = resp.dependentes   ;
        }
      });
    }else{
      console.log('this.rota', this.rota)
      this.consultasService.getAll(this.rota).subscribe(resp => {
        if(resp){
          this.results = resp;
          console.log('this.results', this.results)
        }
      });
    }

    this.getAllAtores();
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
      case 'Dependente':
          rota = '/dependentes';
          break;
      case 'Locação':
        rota = '/locacoes';
        break;
      case 'Consultar Titulo':
        rota = '/titulos';
        break;
      case 'Histórico de Devoluções':
        rota = '/locacoes/devolucoes'
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
          { field: 'categoria', header: 'Categoria', type: 'categoria', isShow: true, isShowForm: true},
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
          { field: 'cpf', header: 'CPF', type: 'text', isShow: false, isShowForm: true},
          { field: 'endereco', header: 'Endereco', type: 'text', isShow: true, isShowForm: true},
          { field: 'telefone', header: 'Telefone', type: 'text', isShow: true, isShowForm: true},

        ];
        break;
      case'Dependente':
          cols = [
            { field: 'nome', header: 'Nome', type: 'text', isShow: true, isShowForm: true},
            { field: 'dtNascimento', header: 'Data de Nascimento', type: 'date', isShow: true, isShowForm: true},
            { field: 'sexo', header: 'Sexo', type: 'sexo', isShow: true, isShowForm: true},
          ];
          break;
      case 'Locação':
        cols = [
          { field: 'item', header: 'Item', type: 'item', isShow: true, isShowForm: true},
          { field: 'cliente', header: 'Cliente', type: 'cliente', isShow: true, isShowForm: true},
          { field: 'valorCobrado', header: 'Valor (R$)', type: 'number', isShow: true, isShowForm: true},
          { field: 'dtLocacao', header: 'Data de Locação', type: 'date', isShow: true, isShowForm: false},
          { field: 'dtDevolucaoPrevista', header: 'Data da Devolução Prevista', type: 'date', isShow: true, isShowForm: true},
        ];
          break;
        case 'Histórico de Devoluções':
          cols = [
            { field: 'item', header: 'Item', type: 'item', isShow: true, isShowForm: true},
            { field: 'cliente', header: 'Cliente', type: 'cliente', isShow: true, isShowForm: true},
            { field: 'valorCobrado', header: 'Valor (R$)', type: 'number', isShow: true, isShowForm: true},
            { field: 'multaCobrada', header: 'Multa (R$)', type: 'number', isShow: true, isShowForm: true},
            { field: 'dtLocacao', header: 'Data de Locação', type: 'date', isShow: true, isShowForm: true},
            { field: 'dtDevolucaoPrevista', header: 'Data da Devolução Prevista', type: 'date', isShow: true, isShowForm: true},
            { field: 'dtDevolucaoEfetiva', header: 'Data da Devolução Efetiva', type: 'date', isShow: true, isShowForm: true},
          ];
            break;
        case 'Consultar Titulo':
          cols = [
            { field: 'nome', header: 'Nome', type: 'text', isShow: true, isShowForm: true},
            { field: 'categoria', header: 'Categoria', type: 'categoria', isShow: true, isShowForm: true},
            { field: 'sinopse', header: 'Sinopse', type: 'text', isShow: true, isShowForm: true},
            { field: 'ano', header: 'Ano', type: 'number', isShow: true, isShowForm: true},
            { field: 'atores', header: 'Atores', type: 'ator', isShow: false, isShowForm: true},
            { field: 'diretor', header: 'Diretor', type: 'diretor', isShow: false, isShowForm: true},
            { field: 'classe', header: 'Classe', type: 'classe', isShow: false, isShowForm: true},
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
        if ((key.startsWith('id') || (this.nomeEntidade == "Cliente" || this.nomeEntidade == "Dependente" )&& key.startsWith('numInscricao'))) {
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


  showConfirmationToCancelarLocacao(entity: any): void {
    this.confirmationService.confirm({
      message: 'Tem certeza que deseja cancelar esta locação?',
      accept: () => {
        // O código que será executado quando o usuário confirmar a exclusão
        this.consultasService.cancelarLocacao(entity.id_locacao, '/locacoes/cancelar').subscribe(resp => {

        });
      },
      reject: () => {
        // O código que será executado quando o usuário cancelar a exclusão (opcional)
      }
    });
  }

  deletarEntidade(entity: any){

    var idValue = this.getIdEntidade(entity);
    this.nomeEntidade == "Cliente" || this.nomeEntidade == "Dependente" ? idValue = entity.numInscricao : '';
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
      } else if((this.nomeEntidade == "Cliente" || this.nomeEntidade == "Dependente") && key.startsWith('numInscricao')){
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
    this.nomeEntidade == 'Cliente' || this.nomeEntidade == 'Dependente'  ? this.idEntidade = entity.numInscricao :  this.idEntidade = this.getIdEntidade(entity);
    console.log('this.idEntidade', this.idEntidade, entity)
    this.rota = this.getRota(this.nomeEntidade);
    this.openDialog = true;
  }

  openModal(){
    this.rota = this.getRota(this.nomeEntidade) + '/adicionar';
    this.idEntidade = 0;
    this.openDialog = true;
  }

  gerenciarDependente(evnt: any){
    // this.openDialogDependentes = true;
    const tipo = 'Dependente'; // Substitua pelo valor real
    const idSocio = evnt.numInscricao; // Substitua pelo valor real
    console.log('tipo', tipo, 'idSocio', idSocio)
    // Use o método navigate para navegar para a rota com parâmetros
    this.router.navigate(['cadastros/dependentes', tipo, idSocio, true]);
  }

  ativarOrDesativarDependente(data: any){
    let dependente = {numInscricao: data.numInscricao, esta_ativo: !data.esta_ativo}
    // console.log('socio', socio)
    this.consultasService.ativarOrDesativarDependente(dependente, this.rota + '/ativarOrDesativar', data.numInscricao).subscribe(resp => {
      this.processarFormulario(resp);
    });
  }

  ativarOrDesativarSocio(data: any){
    let socio = {numInscricao: data.numInscricao, esta_ativo: !data.esta_ativo}
    console.log('socio', socio)
    this.consultasService.ativarOrDesativarSocio(socio, this.rota + '/ativarOrDesativar', data.numInscricao).subscribe(resp => {
      this.processarFormulario(resp);
    }, error => {
      this.messageService.add({ severity: 'error', summary: 'Erro', detail: error });
    });
  }

  onSaveCadastros(event: any){
    console.log(event)
  }

  onClickBtnLeft(){
    this.router.navigate(['cadastros', 'Cliente']);
  }

  devolverLocacao(item: any){
    this.idLocacaoParaEditar = item.id_locacao;
    this.openDialogEfetuarDevolucao = true;
  }

  onDevolverItem(event: any){
    this.openDialogEfetuarDevolucao = false;
    this.idLocacaoParaEditar = 0;
    this.consultasService.getAll(this.rota).subscribe(resp => {
      if(resp){
        this.results = resp;
        console.log('this.results', this.results)
      }
    });
  }

  openLocacao(entity: any){
    this.clienteParaVisualizar = entity;
    this.openVisualizarLocacao = true;
  }


  onChangeConsultarTitulo(){
    this.consultasService.findTituloByNomeCategoriaIdAtor(this.nomeTituloFilter ? this.nomeTituloFilter : '', this.atorFilter && this.atorFilter.nome != 'Todos'? this.atorFilter.nome : '', this.categoriaFilter && this.categoriaFilter != 'Todos' ? this.categoriaFilter : '' ).subscribe(resp => {
      this.results = resp;
    })
  }

  getAllAtores(){
    this.consultasService.getAll('/atores').subscribe(resp => {
      if(resp){
        this.atores = resp;
        this.atores.push({nome: 'Todos'});
        console.log('atores', this.atores);
      }
    });
  }

  visualizarTitulo(entity: any){
    this.tituloParaVisualizar = entity;
    this.openVisualizarLocacao = true;
  }
}
