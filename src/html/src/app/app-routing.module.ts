import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {SimplecarleaseComponent} from './simplecarlease/simplecarlease.component';
import {CarsComponent} from './simplecarlease/cars/cars.component';
import {OrdersComponent} from './simplecarlease/orders/orders.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'simplecarlease',
    pathMatch: 'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'simplecarlease',
    component: SimplecarleaseComponent,
    children: [
      {
        path: '',
        redirectTo: 'cars',
        pathMatch: 'full'
      },
      {
        path: 'cars',
        component: CarsComponent
      },
      {
        path: 'orders',
        component: OrdersComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
