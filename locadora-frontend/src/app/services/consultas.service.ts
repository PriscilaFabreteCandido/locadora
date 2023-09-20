import { HttpClient } from '@angular/common/http';
import { Injectable, Input } from '@angular/core';
import { map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConsultasService {
  private readonly API = '/api';
  constructor(private http: HttpClient) {
  }

  getAll(rota: string){
    return this.http.get<any[]>(`${this.API}` + rota)
    .pipe(map((res: any[]) => {
        let result = [...res];
        return result;
    }));
  }

  create(obj: any, rota: string){
    return this.http.post(`${this.API}` + rota, obj);
  }

  getById(id: number, rota: string){
    return this.http.get(`${this.API}${rota}/${id}`).pipe(map((res: any) => {
      return res;
    }));

  }

  delete(id: number, rota: string){
    return this.http.delete(`${this.API}${rota}/${id}`);
  }

  update(obj: any, rota: string, id: number){
    return this.http.put(`${this.API}${rota}/${id}`, obj).pipe(map((res: any) => {
      return res;
    }));
  }

}
