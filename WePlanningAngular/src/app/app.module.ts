import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule,JsonpModule} from '@angular/http';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {NgxPaginationModule} from 'ngx-pagination';
import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { AboutusComponent } from './Home/aboutus.component';
import { IndexComponent } from './Home/index.component';
import { ContactComponent } from './Home/contact.component';
import { RegisterComponent } from './Home/register.component';
import { PlanComponent } from './Plan/plan.component';
import { Ng2PaginationModule } from 'ng2-pagination';
import { UserComponent } from './User/user.component';
import { ModifyUserComponent } from './User/modifyUser.component';
import { NewPlanComponent } from './Plan/newPlan.component';

import { RouterModule } from '@angular/router';

import { ContactService} from './Services/contact.service';
import { UserService} from './Services/user.service';
import { PlanService } from './Services/plan.service';
import { LoginService } from './Services/login.service';
import { ColisionService } from './Services/colision.service';
import { RouterSecurity } from './Services/routerSecurity.service';

@NgModule({
  declarations: [
    AppComponent, AboutusComponent, IndexComponent, ContactComponent,

    RegisterComponent, PlanComponent, UserComponent, NewPlanComponent, ModifyUserComponent

  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule, JsonpModule,NgbModule.forRoot(), RouterModule.forRoot(routing),
    NgxPaginationModule
  ],
  providers: [ContactService, UserService, PlanService, LoginService, ColisionService, RouterSecurity],
  bootstrap: [AppComponent]
})
export class AppModule {}