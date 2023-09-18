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

  constructor(private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.route.params.subscribe(params => {
      this.nomeEntidade = params['tipo'];
    });

    this.cols = this.getColsByTipoEnt(this.nomeEntidade);
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
          { field: 'dataDevolucao', header: 'Data de Devolução', type: 'date' },
        ];
        break;
    }

    return cols;
  }

  processarFormulario(event: any){
    this.results.push(event);
    this.openDialog = false;
  }

  deletarEntidade(){

  }

  editarEntidade(){

  }
}
