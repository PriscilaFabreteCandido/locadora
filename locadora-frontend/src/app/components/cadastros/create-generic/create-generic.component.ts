import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ConsultasService } from 'src/app/services/consultas.service';

@Component({
  selector: 'create-generic',
  templateUrl: './create-generic.component.html',
  styleUrls: ['./create-generic.component.scss']
})
export class CreateGenericComponent implements OnInit{
  @Input() atributos: any[] = [];
  @Input() idEntidade: any;
  @Output() formularioEnviado = new EventEmitter<any>();
  @Input() rota: string = '';
  @Input() nomeEntidade: string = '';

  idSocio: number = 0;
  public form:FormGroup;
  submitted: boolean = false;
  atores: any[] = [];
  itens: any[] = [];
  diretores: any[] = [];
  classes: any[] = [];
  titulos: any[] = [];
  clientes: any[] = [];

  selectedAtores: any[] = [];
  selectedItens: any[] = [];
  selectedDiretor: any;
  selectedClasse: any;
  selectedTitulo: any;
  selectedCliente: any;
  selectedSexo: any;
  selectedItem: any;
  sexos: any[] = ['Feminino', 'Masculino']

  entidade: any;


  constructor(private fb:FormBuilder,
              private consultasService: ConsultasService,
              private route: ActivatedRoute,
              private router: Router,) {
      this.form = fb.group({});
  }

  ngOnInit() {
    this.form = this.fb.group({});

    this.atributos.forEach((atributo) => {
      if(atributo.isShowForm){
        this.form.addControl(atributo.field, this.fb.control(null, Validators.required));
      }
    });

    if(this.idEntidade > 0){
      this.getById();
    }else{
      this.getAllEntidades();
    }

     this.route.params.subscribe(params => {
        if(params['idSocio']){
          this.idSocio = params['idSocio'];
        }
      });

  }

  getAllEntidades(){
    switch(this.nomeEntidade){
      case  'Titulo':
        this.getAllAtores();
        this.getAllClasses();
        this.getAllDiretores();
        this.getAllItens();
        break;
      case 'Item':
        this.getAllTitulos();
        break;
      case 'Locação':
          this.getAllItens();
          this.getAllClientes();
        break;
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

  getById(){
    this.consultasService.getById(this.idEntidade, this.rota).subscribe(resp => {
      if(resp){
        this.entidade = resp;
        this.preencherForm(this.entidade);
        this.getAllEntidades();
      }
    });
  }

  preencherForm(entity: any){

    this.atributos.forEach(atributo => {
      for (const key in entity) {
         if(key == atributo.field){
          this.form.patchValue({
            [atributo.field]: entity[key]
          });
         }
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.submitted = false;
      var formData: any = this.form.value;

      // Verifique se há atores selecionados
      if (this.selectedAtores && this.selectedAtores.length > 0) {
        // Extraia os objetos AtorDTO dos atores selecionados
        formData.listaAtores = this.selectedAtores;
      } else {
        // Se nenhum ator foi selecionado, defina listaAtores como uma lista vazia
        formData.listaAtores = [];
      }

      if(this.nomeEntidade == 'Dependente'){
        let socio = {numInscricao: this.idSocio};
        formData.socio = socio;
      }

      if (!this.idEntidade || this.idEntidade <= 0) {
        this.consultasService.create(formData, this.rota).subscribe((resp) => {
          this.formularioEnviado.emit(resp);
          this.form.reset();
        });
      } else {
        this.consultasService.update(formData, this.rota + '/editar', this.idEntidade).subscribe((resp) => {
          this.formularioEnviado.emit(resp);
          this.form.reset();
        });
      }
    } else {
      this.submitted = true;
    }
  }


  getAllAtores(){
    this.consultasService.getAll('/atores').subscribe(resp => {
      if(resp){
        this.atores = resp;

        if (this.idEntidade > 0) {
          const idsParaFiltrar: number[] = this.entidade.listaAtores.map((x: any) => x.id_ator); // Certifique-se de que 'id_ator' seja do tipo correto (por exemplo, number)
          this.selectedAtores = this.atores.filter(ator => idsParaFiltrar.includes(ator.id_ator));

          // this.atores.sort((d1, d2) => {
          //   if (d1.nome === this.selectedAtores.nome) {
          //       return -1; // selectedAtores vem antes
          //   } else if (d2.nome === this.selectedAtores.nome) {
          //       return 1; // selectedAtores vem depois
          //   } else {
          //       return d1.nome.localeCompare(d2.nome); // ordenar pelo nome
          //   }
          // });
        }

      }
    });
  }

  getAllDiretores(){
    this.consultasService.getAll('/diretores').subscribe(resp => {
      if(resp){
        this.diretores = resp;

        if (this.idEntidade > 0) {
          console.log('diretores', this.diretores, this.idEntidade)
          this.selectedDiretor = this.diretores.filter(x => x.id_diretor == this.entidade.diretor.id_diretor)[0];

          this.diretores.sort((d1, d2) => {
            if (d1.nome === this.selectedDiretor.nome) {
                return -1; // selectedDiretor vem antes
            } else if (d2.nome === this.selectedDiretor.nome) {
                return 1; // selectedDiretor vem depois
            } else {
                return d1.nome.localeCompare(d2.nome); // ordenar pelo nome
            }
          });

          console.log('this.diretores', this.diretores)

        }
      }
    });
  }

  getAllClasses(){
    this.consultasService.getAll('/classes').subscribe(resp => {
      if(resp){
        this.classes = resp;

        if (this.idEntidade > 0) {
          this.selectedClasse = this.classes.filter(x => x.id_classe == this.entidade.classe.id_classe)[0];

          this.classes.sort((d1, d2) => {
            if (d1.nome === this.selectedClasse.nome) {
                return -1;
            } else if (d2.nome === this.selectedClasse.nome) {
                return 1;
            } else {
                return d1.nome.localeCompare(d2.nome);
            }
          });
        }
      }
    });
  }

  getAllItens(){
    this.consultasService.getAll('/itens').subscribe(resp => {
      if(resp){
        this.itens = resp;
        console.log('this.itens', this.itens)

      }
    });
  }

  getAllTitulos(){
    this.consultasService.getAll('/titulos').subscribe(resp => {
      if(resp){
        this.titulos = resp;

        if (this.idEntidade > 0) {
          this.selectedTitulo = this.titulos.filter(x => x.id_classe == this.entidade.titulo.nome)[0];

          this.classes.sort((d1, d2) => {
            if (d1.nome === this.selectedTitulo.nome) {
                return -1;
            } else if (d2.nome === this.selectedTitulo.nome) {
                return 1;
            } else {
                return d1.nome.localeCompare(d2.nome);
            }
          });
        }
      }
    });
  }

  getAllClientes(){
    this.consultasService.getAll('/socios').subscribe((resp: any) => {
      if(resp){
        this.clientes = [];
        resp.forEach((x: any) => {
          this.clientes.push(x);

          if(x.dependentes && x.dependentes.length > 0){
            for(let i = 0; i < x.dependentes.length; i++){
              this.clientes.push(x.dependentes[i]);
            }
          }

        });

        if (this.idEntidade > 0) {
          this.selectedClasse = this.classes.filter(x => x.id_classe == this.entidade.classe.id_classe)[0];

          this.classes.sort((d1, d2) => {
            if (d1.nome === this.selectedClasse.nome) {
                return -1;
            } else if (d2.nome === this.selectedClasse.nome) {
                return 1;
            } else {
                return d1.nome.localeCompare(d2.nome);
            }
          });
        }
      }
    });

  }

  zeroEsquerda(numero: any) {
    return numero < 10 ? '0' + numero : numero;
  }

  formatarData(data: any) {
      let dia = this.zeroEsquerda(data.getDate());
      let mes = this.zeroEsquerda(data.getMonth() + 1); // Mês é baseado em zero
      let ano = data.getFullYear();

      return ano + '-' + mes + '-' + dia;
  }

  onChangeItem(){
    let valor = this.selectedItem?.titulo?.classe?.valor;

    this.form.get('valorCobrado')?.setValue(valor ? valor : 0);
    let dataAtual = new Date();
    let qtdDias = this.selectedItem?.titulo?.classe?.prazoDevolucao;
    let dataPrevista = new Date(dataAtual.getTime() + qtdDias * 24 * 60 * 60 * 1000);

    let dataFormatada = this.formatarData(dataPrevista);
    this.form.get('dtDevolucaoPrevista')?.setValue(dataFormatada);
  }
}
