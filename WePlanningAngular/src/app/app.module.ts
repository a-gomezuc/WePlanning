import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule,JsonpModule} from '@angular/http';
import { AppComponent } from './app.component';
import { AboutusComponent } from './Home/aboutus.component';
import { IndexComponent } from './Home/index.component';
import { ContactComponent } from './Home/contact.component';
import { RegisterComponent } from './Home/register.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { routing } from './app.routing';

import { RouterModule } from '@angular/router';

import { UserService} from './Services/user.service';
import { PlanService } from './Services/plan.service';
import { LoginService } from './Services/login.service';
@NgModule({
  declarations: [
    AppComponent, AboutusComponent, IndexComponent, ContactComponent, RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule, JsonpModule,NgbModule.forRoot(), RouterModule.forRoot(routing)
  ],
  providers: [UserService, PlanService, LoginService],
  bootstrap: [AppComponent]
})
export class AppModule {}