import { Routes } from '@angular/router';
import { IndexComponent } from './Home/index.component';
import { AboutusComponent } from './Home/aboutus.component';
import { ContactComponent } from './Home/contact.component';
import { RegisterComponent } from './Home/register.component';
import { PlanComponent } from './Plan/plan.component';
import { ModifyUserComponent } from './User/modifyUser.component';
import { UserComponent } from './User/user.component';
import { ModifyPlanComponent } from './Plan/modifyPlan.component';
import { ModifyPhotoComponent } from './User/modifyPhoto.component';

import { NewPlanComponent } from './Plan/newPlan.component';

import { RouterSecurity } from './Services/routerSecurity.service';


export const  routing : Routes = [
    { path: 'index', component: IndexComponent },
    { path: 'index/search/:title/:place/:category', component: IndexComponent },
    { path: 'aboutus', component: AboutusComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'plan/:id', component: PlanComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'modifyPlan/:id', component: ModifyPlanComponent,canActivate:[RouterSecurity] },
    { path: 'profile/:id', component: UserComponent },
    { path: 'modifyProfile/:id', component: ModifyUserComponent,canActivate:[RouterSecurity] },
    { path: 'modifyProfilePhoto/:id', component: ModifyPhotoComponent, },
    { path: 'newPlan', component: NewPlanComponent, canActivate:[RouterSecurity] },
    { path: '', redirectTo: 'index', pathMatch: 'full' }

];
