import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule,JsonpModule} from '@angular/http';
import { AppComponent } from './app.component';
import { AboutusComponent } from './aboutus.component';
import { IndexComponent } from './index.component';
import { ProfileComponent} from './profile.component';
import { ContactComponent } from './contact.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { routing } from './app.routing';
@NgModule({
  declarations: [
    AppComponent, AboutusComponent, IndexComponent, ContactComponent, ProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule, JsonpModule,NgbModule.forRoot(), routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}