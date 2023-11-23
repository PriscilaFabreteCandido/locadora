import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CadastrosComponent } from './components/cadastros/cadastros.component';
import { EfetuarLocacaoComponent } from './components/locacao/efetuar-locacao/efetuar-locacao.component';
import { EfetuarDevolucaoComponent } from './components/locacao/efetuar-devolucao/efetuar-devolucao.component';

const routes: Routes = [
  { path: 'cadastros/:tipo', component: CadastrosComponent },
  { path: 'efetuarLocacao', component: EfetuarLocacaoComponent },
  { path: 'efetuarDevolucao', component: EfetuarDevolucaoComponent },
  { path: 'cadastros/dependentes/:tipo/:idSocio/:showBtnLeft', component: CadastrosComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {


}
