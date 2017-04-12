import { Routes } from '@angular/router';
import { IndexComponent } from './Home/index.component';
import { AboutusComponent } from './Home/aboutus.component';
import { ContactComponent } from './Home/contact.component';
import { RegisterComponent } from './Home/register.component';

export const  routing : Routes = [
    { path: 'index', component: IndexComponent } ,
    { path: 'aboutus', component: AboutusComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'register', component: RegisterComponent },
    { path: '', redirectTo: 'index', pathMatch: 'full' }
];
