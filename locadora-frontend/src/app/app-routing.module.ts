import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrosComponent } from './components/cadastros/cadastros.component';

const routes: Routes = [
  { path: 'cadastros/:tipo', component: CadastrosComponent },
  { path: 'cadastros/dependentes/:tipo/:idSocio/:showBtnLeft', component: CadastrosComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {


}
