import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule,JsonpModule} from '@angular/http';
import { AppComponent } from './app.component';
import { AboutusComponent } from './Home/aboutus.component';
import { IndexComponent } from './Home/index.component';
import { ContactComponent } from './Home/contact.component';
import { RegisterComponent } from './Home/register.component';
import { PlanComponent } from './Plan/plan.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {Ng2PaginationModule} from 'ng2-pagination';
import { routing } from './app.routing';
import { UserComponent } from './User/user.component';
import { RouterModule } from '@angular/router';

import { UserService} from './Services/user.service';
import { PlanService } from './Services/plan.service';
import { LoginService } from './Services/login.service';

@NgModule({
  declarations: [
    AppComponent, AboutusComponent, IndexComponent, ContactComponent,
    RegisterComponent, PlanComponent, UserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule, JsonpModule,NgbModule.forRoot(), RouterModule.forRoot(routing),
    Ng2PaginationModule
  ],
  providers: [UserService, PlanService, LoginService],
  bootstrap: [AppComponent]
})
export class AppModule {}