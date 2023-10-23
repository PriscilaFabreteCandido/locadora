import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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

  public form:FormGroup;
  submitted: boolean = false;

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
        let obj = resp;
        this.preencherForm(obj);
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

    })



  }

  onSubmit() {
    if (this.form.valid) {
      this.submitted = false;
      const formData = this.form.value;

      if(!this.idEntidade || this.idEntidade <= 0){
        this.consultasService.create(formData, this.rota).subscribe(resp => {
          this.formularioEnviado.emit(resp);
          this.form.reset();
        });
      }else{
        console.log('formData', formData)
        this.consultasService.update(formData, this.rota + '/editar', this.idEntidade).subscribe(resp => {
          this.formularioEnviado.emit(resp);
          this.form.reset();
        });
      }

    } else {
      this.submitted = true;
    }
  }
}

