
import { Component, NgModule } from '@angular/core';
import  {  Router,  ActivatedRoute  }  from  '@angular/router';

import { ContactService } from '../Services/contact.service';

import { Http } from '@angular/http';
import { Contact } from '../Class/contact.model';

@Component({
  selector: 'app-root',
  templateUrl: './contact.component.html',
  styleUrls: ['../app.component.css'],
})
export class ContactComponent {

  constructor(private http: Http, private contactService: ContactService, private router: Router) { }

  newContact(firstname: string, lastname: string, telephone:number,email: string, company: string, message:string) {
    let contact = new Contact(firstname, lastname, telephone, email, company, message);

    this.contactService.newContact(contact).subscribe(
      result => {
        console.log(contact);
        this.router.navigate(['/index']);
      },
      error => alert(error)
    );
  }
}