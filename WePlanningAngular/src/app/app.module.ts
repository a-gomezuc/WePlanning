import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { AboutusComponent } from './aboutus.component';
import { IndexComponent } from './index.component';
import { ContactComponent } from './contact.component';
import { routing } from './app.routing';
@NgModule({
  declarations: [
    AppComponent, AboutusComponent, IndexComponent, ContactComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule, routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}