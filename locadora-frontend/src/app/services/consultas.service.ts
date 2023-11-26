import { HttpClient } from '@angular/common/http';
import { Injectable, Input } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { Observable, catchError, map, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsultasService {
  private readonly API = 'http://localhost:8080/api';
  constructor(private http: HttpClient,
    private messageService: MessageService,
    private confirmationService: ConfirmationService,
    ) {
  }

  getAll(rota: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.API}` + rota)
      .pipe(
        map((res: any[]) => [...res]),
        catchError(this.handleError)
      );
  }

  create(obj: any, rota: string): Observable<any> {
    return this.http.post(`${this.API}` + rota, obj)
      .pipe(
        catchError(this.handleError)
      );
  }

  getById(id: number, rota: string): Observable<any> {
    return this.http.get(`${this.API}${rota}/${id}`)
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  delete(id: number, rota: string): Observable<any> {
    return this.http.delete(`${this.API}${rota}/${id}`)
      .pipe(
        catchError(this.handleError)
      );
  }

  update(obj: any, rota: string, id: number): Observable<any> {
    return this.http.put(`${this.API}${rota}/${id}`, obj)
      .pipe(
        catchError(this.handleError)
      );
  }

  ativarOrDesativarSocio(obj: any, rota: string, id: number){
    return this.http.put(`${this.API}${rota}/${id}`, obj)
      .pipe(
        catchError(this.handleError)
      );
  }

  ativarOrDesativarDependente(obj: any, rota: string, id: number){
    return this.http.put(`${this.API}${rota}/${id}`, obj)
    .pipe(
      catchError(this.handleError)
    );
  }

  cancelarLocacao(id: number, rota: string): Observable<any> {
    return this.http.delete(`${this.API}${rota}/${id}`)
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  obterMulta(id: number): Observable<any> {
    return this.http.get(`${this.API}${'/locacoes/obterMulta'}/${id}`)
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  devolverItem(id: number): Observable<any> {
    return this.http.get(`${this.API}${'/locacoes/devolver'}/${id}`)
      .pipe(
        map((res: any) => res),
        catchError(this.handleError)
      );
  }

  private handleError(error: any): Observable<never> {
    console.log('error', error)
    let errorMessage = 'Ocorreu um erro no serviço.';

    if (error.error ) {
      // Erro do lado do cliente
      errorMessage = `Erro: ${error.error.message}`;
    }

    alert(errorMessage)
    // Exibir a mensagem de erro usando o MessageService

    return throwError(errorMessage);
  }
}
