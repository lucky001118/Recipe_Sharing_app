import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private baseUrl = "http://localhost:5454"
  constructor(private http:HttpClient) { }

  // with the help of this BehaviorSubject we can acces this values in anywhere in our application
  authSubject = new BehaviorSubject<any>({
    user:null
  })

  login(userData:any):Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/auth/sigin`,userData)
  }

  register(userData:any):Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/auth/signup`,userData)
  }

  getUserProfile():Observable<any>{
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem("jwt")}`
    })
    return this.http.get<any>(`${this.baseUrl}/api/users/profile`,{headers}).pipe(
      tap((user)=>{
        console.log("get user profile ",user)
        const currentState=this.authSubject.value;
        this.authSubject.next({...currentState,user})
      })
    )
  }

  logout(){
    localStorage.clear();
    this.authSubject.next({})
  }
}
