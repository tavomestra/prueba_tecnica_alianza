import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment.prod';
import { Cliente } from 'src/app/model/clientes';
import { HttpClient } from '@angular/common/http';

enum EndPoint {
  CLIENTE = 'cliente',
}

@Injectable({
  providedIn: 'root',
})
export class ClienteService {
  constructor(private http: HttpClient) {}

  private getUrlService(endPoint: EndPoint) {
    return environment.apiUrl + endPoint;
  }

  /**
   * Obtener todos los clientes.
   */
  getAll() {
    return this.http.get<Cliente[]>(
      this.getUrlService(EndPoint.CLIENTE) + `/get-all`
    );
  }

  /**
   * Obtener por sharedKey.
   * @param sharedKey
   */
  getBySharedKey(sharedKey : string){
    return this.http.get<Cliente>(
      this.getUrlService(EndPoint.CLIENTE) + `/get-by-sharedkey/${sharedKey}`
    );
  }

  /**
   * Crear cliente.
   * @param cliente 
   */
  create(cliente : Cliente){
    return this.http.post<any>(
        this.getUrlService(EndPoint.CLIENTE) + `/add`,
        cliente
      );

  }

  /**
   * Obtener todos los clientes.
   */
  getByFilters(cliente : Cliente) {
    return this.http.post<Cliente[]>(
      this.getUrlService(EndPoint.CLIENTE) + `/get-by-filters`,
      cliente
    );
  }
}
