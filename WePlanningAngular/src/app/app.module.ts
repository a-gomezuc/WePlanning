import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule,JsonpModule} from '@angular/http';
import { AppComponent } from './app.component';
import { AboutusComponent } from './aboutus.component';
import { IndexComponent } from './index.component';
import { ContactComponent } from './contact.component';
import { RegisterComponent } from './register.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { routing } from './app.routing';

import { UserService} from './user.service';
import { PlanService } from './plan.service';
@NgModule({
  declarations: [
    AppComponent, AboutusComponent, IndexComponent, ContactComponent, RegisterComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule, JsonpModule,NgbModule.forRoot(), routing
  ],
  providers: [UserService, PlanService],
  bootstrap: [AppComponent]
})
export class AppModule {}