import { Injectable, Inject, forwardRef } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Observable';

import { PlanService } from './plan.service';
import { User } from '../Class/user.model';
import { Plan } from '../Class/plan.model';
import { Contact } from '../Class/contact.model';
import 'rxjs/Rx';

@Injectable()
export class ContactService {

    constructor(private http: Http) { }
    
        newContact(contact:Contact) {
        return this.http.post("https://localhost:8443/api/addContact",contact)
            .map(response => { return response.json() })
            .catch(error => this.handleError(error))
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