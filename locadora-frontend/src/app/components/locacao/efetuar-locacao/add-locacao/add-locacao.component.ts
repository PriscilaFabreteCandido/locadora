import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ConsultasService } from 'src/app/services/consultas.service';

@Component({
  selector: 'app-add-locacao',
  templateUrl: './add-locacao.component.html',
  styleUrls: ['./add-locacao.component.scss']
})
export class AddLocacaoComponent {
  @Input() atributos: any[] = [];
  @Input() idEntidade: any;
  @Output() formularioEnviado = new EventEmitter<any>();
  @Input() rota: string = '';
  @Input() nomeEntidade: string = '';

  public form:FormGroup;
  submitted: boolean = false;
  atores: any[] = [];
  itens: any[] = [];
  diretores: any[] = [];
  classes: any[] = [];
  titulos: any[] = [];

  selectedAtores: any[] = [];
  selectedItens: any[] = [];
  selectedDiretor: any;
  selectedClasse: any;
  selectedTitulo: any;

  entidade: any;

  constructor(private fb:FormBuilder,
              private consultasService: ConsultasService) {
      this.form = fb.group({});
  }

  ngOnInit() {
    this.form = this.fb.group({});

    this.atributos.forEach((atributo) => {
      this.form.addControl(atributo.field, this.fb.control(null, Validators.required));
    });

    if(this.idEntidade > 0){
      this.getById();

    }else{
      this.getAllEntidades();
    }


  }

  getAllEntidades(){
    switch(this.nomeEntidade){
      case  'Titulo':
        this.getAllDiretores();
        break;
      case 'Item':
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
      const formData = this.form.value;

      // Verifique se há atores selecionados
      if (this.selectedAtores && this.selectedAtores.length > 0) {
        // Extraia os objetos AtorDTO dos atores selecionados
        formData.listaAtores = this.selectedAtores;
      } else {
        // Se nenhum ator foi selecionado, defina listaAtores como uma lista vazia
        formData.listaAtores = [];
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


}

