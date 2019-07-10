import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';


const httpOptions = {
  headers: new HttpHeaders({
    'Accept':  'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class PayrollService {
	
	payrollUrl = 'http://localhost:8080/consulta-nomina-api/webresources/consulta.nomina.api.persona';

  constructor(private http: HttpClient) {};
	getPayroll(){
		return this.http.get(this.payrollUrl, httpOptions);
	}
	
}
