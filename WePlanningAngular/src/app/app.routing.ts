import { Routes, RouterModule } from '@angular/router';
import { IndexComponent } from './index.component';
import { AboutusComponent } from './aboutus.component';
import { ContactComponent } from './contact.component';
import {ProfileComponent } from './profile.component';
const appRoutes = [
    { path: 'index', component: IndexComponent } ,
    { path: 'aboutus', component: AboutusComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'profile', component: ProfileComponent },
    { path: '', redirectTo: 'index', pathMatch: 'full' }
]
export const  routing = RouterModule.forRoot(appRoutes);
