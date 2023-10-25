import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.scss']
})
export class MenubarComponent implements OnInit{

  items: any | undefined;

  constructor(private router: Router){

  }
  ngOnInit(){
    this.items = [
      {
          label: 'Cadastros',
          icon: 'pi pi-fw pi-file',
          items: [
              {
                  label: 'Ator',
                  icon: 'pi pi-fw pi-plus',
                  command: () => {
                    this.router.navigate(['/cadastros/Ator']);
                }
              },
              {
                  label: 'Classe',
                  icon: 'pi pi-fw pi-trash',
                  command: () => {
                    this.router.navigate(['/cadastros/Classe']);
                }
              },
              {
                  label: 'Diretor',
                  icon: 'pi pi-fw pi-external-link',
                  command: () => {
                    this.router.navigate(['/cadastros/Diretor']);
                }
              },
              {
                label: 'Item',
                icon: 'pi pi-fw pi-external-link',
                command: () => {
                  this.router.navigate(['/cadastros/Item']);
                },
              },
              {
                label: 'Título',
                icon: 'pi pi-fw pi-external-link',
                command: () => {
                  this.router.navigate(['/cadastros/Titulo']);
                }
              }
          ]
      },
    ];

  }
}
