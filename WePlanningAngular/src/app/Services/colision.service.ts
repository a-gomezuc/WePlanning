import { Injectable, Inject, forwardRef } from '@angular/core';
import { Http, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
@Injectable()
export class ColisionService {
    constructor(private http: Http){}

        getUser(id: string) {
        return this.http.get("https://localhost:8443/api/user/" + id)
            .map(response => response.json())
            .catch(error => this.handleError(error));
    }
       private handleError(error: any) {
        console.error(error);
        switch (error.status) {
            case 409:
                return Observable.throw("Server error (" + error.status + "): El nombre de usuario ya esta en uso");
            case 404:
                return Observable.throw("Server error (" + error.status + "): Ha ocurrido algun error vuelva a intentarlo");
            case 401:
                return Observable.throw("Server error (" + error.status + "): No esta autorizado para realizar esa acción.");
            case 406:
                return Observable.throw("Server error (" + error.status + "): Rellene los campos correctamente 'Provincia' o 'Categoría' correctamente");
        }
    }
}