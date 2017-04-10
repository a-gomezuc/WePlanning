import { Routes } from '@angular/router';
import { IndexComponent } from './index.component';
import { AboutusComponent } from './aboutus.component';
import { ContactComponent } from './contact.component';
import { RegisterComponent } from './register.component';

export const  routing : Routes = [
    { path: 'index', component: IndexComponent } ,
    { path: 'aboutus', component: AboutusComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'register', component: RegisterComponent },
    { path: '', redirectTo: 'index', pathMatch: 'full' },
    { path: '**', redirectTo: 'index' }
];
