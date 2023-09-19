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
  }

  onSubmit() {
    if (this.form.valid) {
      this.submitted = false;
      const formData = this.form.value;

      console.log('formData', formData)
      this.consultasService.create(formData, this.rota).subscribe(resp => {
        this.formularioEnviado.emit(formData);
        this.form.reset();
      });

    } else {
      this.submitted = true;
    }
  }
}
