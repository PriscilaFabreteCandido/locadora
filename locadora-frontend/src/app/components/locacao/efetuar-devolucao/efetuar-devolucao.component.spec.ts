import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EfetuarDevolucaoComponent } from './efetuar-devolucao.component';

describe('EfetuarDevolucaoComponent', () => {
  let component: EfetuarDevolucaoComponent;
  let fixture: ComponentFixture<EfetuarDevolucaoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EfetuarDevolucaoComponent]
    });
    fixture = TestBed.createComponent(EfetuarDevolucaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
