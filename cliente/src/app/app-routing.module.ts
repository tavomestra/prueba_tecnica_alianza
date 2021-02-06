import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainClienteComponent } from './components/cliente/main-cliente/main-cliente.component';
import { NuevoClienteComponent } from './components/cliente/nuevo-cliente/nuevo-cliente.component';


const routes: Routes = [
  {path : "home", component : MainClienteComponent},  
  {path : "nuevo-cliente", component : NuevoClienteComponent},  
  { path: "**", redirectTo: "home", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
