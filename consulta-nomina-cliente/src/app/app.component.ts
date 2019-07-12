import { Component } from '@angular/core';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css']
})
export class AppComponent {

	nombre;
	id;
	sueldo;
	input;

	search(valor: String) {
		if (valor === "") {
			return;
		}

		fetch("http://localhost:8080/darUnEmpleadoAlTunel/?name=" + valor, {
			method: 'GET',
			mode: 'cors'
		}).then(response => response.json())
			.then(myJson => {
				console.log(myJson);
				this.nombre = myJson["nombre"];
				this.id = myJson["id"];
				this.sueldo = myJson["salario"];
			});
	};
}
