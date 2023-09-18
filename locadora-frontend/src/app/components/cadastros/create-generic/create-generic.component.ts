import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'create-generic',
  templateUrl: './create-generic.component.html',
  styleUrls: ['./create-generic.component.scss']
})
export class CreateGenericComponent implements OnInit{
  @Input() atributos: any[] = [];
  @Input() modelGeneric: any;
  @Output() formularioEnviado = new EventEmitter<any>();

  public form:FormGroup;
  submitted: boolean = false;

  constructor(private fb:FormBuilder) {
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

      this.formularioEnviado.emit(formData);
      // Limpe o formulário após o envio, se necessário
      this.form.reset();
    } else {
      this.submitted = true;
    }
  }
}
