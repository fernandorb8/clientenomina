import { Component } from '@angular/core';
import { Person } from "./person"

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
//  title = 'consulta-nomina-cliente';
	personel;
  search() {
	this.personel = [new Person("Queso",1), new Person("Galletas",2)];
	console.log("Actualizado")
};
}
