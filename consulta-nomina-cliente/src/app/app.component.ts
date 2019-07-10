import { Component } from '@angular/core';
import { Person } from "./person"
import {PayrollService } from "./payroll.service"

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
//  title = 'consulta-nomina-cliente';
	
	payrollService;	
	personel;
	
	constructor (payrollService: PayrollService){
		this.payrollService = payrollService;
	}
	
  search() {
	this.payrollService.getPayroll().subscribe((data) => this.personel = data)
//	this.personel = [new Person("Queso",1), new Person("Galletas",2)];
	console.log("Actualizado")
};
}
