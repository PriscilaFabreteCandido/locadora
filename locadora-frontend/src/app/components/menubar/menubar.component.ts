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
          icon: 'pi pi-plus-circle',
          items: [
              {
                  label: 'Ator',
                  icon: 'pi pi-android',
                  command: () => {
                    this.router.navigate(['/cadastros/Ator']);
                }
              },
              {
                  label: 'Classe',
                  icon: 'pi pi-briefcase',
                  command: () => {
                    this.router.navigate(['/cadastros/Classe']);
                }
              },
              {
                  label: 'Diretor',
                  icon: 'pi pi-megaphone',
                  command: () => {
                    this.router.navigate(['/cadastros/Diretor']);
                }
              },
              {
                label: 'Item',
                icon: 'pi pi-th-large',
                command: () => {
                  this.router.navigate(['/cadastros/Item']);
                },
              },
              {
                label: 'Título',
                icon: 'pi pi-file',
                command: () => {
                  this.router.navigate(['/cadastros/Titulo']);
                }
              },
              {
                label: 'Cliente',
                icon: 'pi pi-users',
                command: () => {
                  this.router.navigate(['/cadastros/Cliente']);
                }
              },
          ]
      },
      {
        label: 'Locação',
        icon: 'pi pi-sign-in',
        items: [
            {
                label: 'Efetuar Locação',
                icon: 'pi pi-shopping-cart',
                command: () => {
                  this.router.navigate(['/cadastros/Locação']);
              }
            },
            {
              label: 'Histórico de Devoluções',
              icon: 'pi pi-history',
              command: () => {
                this.router.navigate(['cadastros/Histórico de Devoluções']);
            }
          },
        ]
      },
      {
        label: 'Consultas',
        icon: 'pi pi-search',
        items: [
            {
                label: 'Titulo',
                icon: 'pi pi-file',
                command: () => {
                  this.router.navigate(['/cadastros/Ator']);
              }
            },
        ]
      },
    ];

  }
}
