import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {SimplecarleaseComponent} from './simplecarlease/simplecarlease.component';
import {CarsComponent} from './simplecarlease/cars/cars.component';
import {LoginComponent} from './login/login.component';
import {OrdersComponent} from './simplecarlease/orders/orders.component';
import {Postman} from '../helper/postman';
import {Cfg} from '../config/cfg';
import {Ctx} from '../context/ctx';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';


@NgModule({
  declarations: [
    AppComponent,
    SimplecarleaseComponent,
    CarsComponent,
    LoginComponent,
    OrdersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule
  ],
  providers: [
    Postman,
    Cfg,
    Ctx
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
