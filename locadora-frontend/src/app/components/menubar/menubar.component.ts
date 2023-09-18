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
              }
          ]
      },
      {
          label: 'Edit',
          icon: 'pi pi-fw pi-pencil',
          items: [
              {
                  label: 'Left',
                  icon: 'pi pi-fw pi-align-left'
              },
              {
                  label: 'Right',
                  icon: 'pi pi-fw pi-align-right'
              },
          ]
      },
      {
          label: 'Users',
          icon: 'pi pi-fw pi-user',
          items: [
              {
                  label: 'New',
                  icon: 'pi pi-fw pi-user-plus'
              },
              {
                  label: 'Delete',
                  icon: 'pi pi-fw pi-user-minus'
              },
              {
                  label: 'Search',
                  icon: 'pi pi-fw pi-users',

              }
          ]
      },
      {
          label: 'Events',
          icon: 'pi pi-fw pi-calendar',
          items: [
              {
                  label: 'Edit',
                  icon: 'pi pi-fw pi-pencil',
              },
              {
                  label: 'Archieve',
                  icon: 'pi pi-fw pi-calendar-times',
                  items: [
                      {
                          label: 'Remove',
                          icon: 'pi pi-fw pi-calendar-minus'
                      }
                  ]
              }
          ]
      },
      {
          label: 'Quit',
          icon: 'pi pi-fw pi-power-off'
      }
    ];

  }
}
