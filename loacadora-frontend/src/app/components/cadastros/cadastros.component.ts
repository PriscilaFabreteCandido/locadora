import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'cadastros',
  templateUrl: './cadastros.component.html',
  styleUrls: ['./cadastros.component.scss']
})
export class CadastrosComponent implements OnInit{

  cols: any[] = ["nome"];
  results: any[] = [];
  nomeEntidade: string = "";

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
          { field: 'nome', header: 'Nome' },
        ];
        break;
      case 'Diretor':
        cols = [
          { field: 'nome', header: 'Nome' },
        ];
        break;
      case 'Classe':
        cols = [
          { field: 'nome', header: 'Nome' },
          { field: 'valor', header: 'Valor' },
          { field: 'dataDevolucao', header: 'Data de Devolução' },
        ];
        break;
    }

    console.log('cols', cols)
    return cols;
  }
}
