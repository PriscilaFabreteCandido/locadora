import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { DialogModule } from 'primeng/dialog';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TableModule } from 'primeng/table';
import { CadastrosComponent } from './components/cadastros/cadastros.component';
import { MegaMenuModule } from 'primeng/megamenu';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { MenubarComponent } from './components/menubar/menubar.component';
import { MenubarModule } from 'primeng/menubar';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CreateGenericComponent } from './components/cadastros/create-generic/create-generic.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { InputNumberModule } from 'primeng/inputnumber';
@NgModule({
  declarations: [
    AppComponent,
    CadastrosComponent,
    NavBarComponent,
    MenubarComponent,
    CreateGenericComponent
  ],
  imports: [
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    TableModule,
    MegaMenuModule,
    MenubarModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    FormsModule,
    ReactiveFormsModule,
    InputNumberModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
