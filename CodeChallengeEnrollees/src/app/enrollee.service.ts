import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Enrollee } from './enrollee';

@Injectable({
  providedIn: 'root'
})
export class EnrolleeService {

  private baseUrl = 'http://localhost:8080/enrollees';

  constructor(private http: HttpClient) { }

  getEnrollee(id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateEnrollee(id: string, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  getEnrolleesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
