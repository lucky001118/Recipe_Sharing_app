import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeServiceService {

  private baseUrl = "http://localhost:5454"
  constructor(private http:HttpClient) { }

  // with the help of this BehaviorSubject we can acces this values in anywhere in our application
  recipeSubject = new BehaviorSubject<any>({
    recipes:[],
    loding:false,
    newRecipe:null
  })

  private getHeaders():HttpHeaders{
    const token = localStorage.getItem("jwt")
    return new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem("jwt")}`
    });
  }

  getRecipes():Observable<any>{
    const headers = this.getHeaders()
    return this.http.get(`${this.baseUrl}/api/recipe`,{headers}).pipe(
      tap((recipes)=>{
        const currentState=this.recipeSubject.value;
        this.recipeSubject.next({...currentState,recipes})
      })
    );
  }

  createRecipes(recipeData:any):Observable<any>{
    const headers = this.getHeaders()
    return this.http.post(`${this.baseUrl}/api/recipe`,recipeData,{headers}).pipe(
      tap((newRecipe)=>{
        const currentState=this.recipeSubject.value;
        this.recipeSubject.next({...currentState,recipes:
          [newRecipe,...currentState.recipes] })
      })
    )
  }

  updateRecipe(recipeData:any):Observable<any>{
    const headers = this.getHeaders()
    return this.http.put(`${this.baseUrl}/api/recipe/${recipeData.id}`,recipeData,{headers}).pipe(
      tap((updateRecipe:any)=>{
        const currentState=this.recipeSubject.value;
        const updateRecipes = currentState.recipes.map((item:any)=>item.id===updateRecipe.id?updateRecipe:item)
        this.recipeSubject.next({...currentState,updateRecipes})
      })
    )
  }

  deleteRecipe(id:any):Observable<any>{
    const headers = this.getHeaders()
    return this.http.delete(`${this.baseUrl}/api/recipe/${id}`,{headers}).pipe(
      tap((deletedRecipe:any)=>{
        const currentState=this.recipeSubject.value;
        const updateRecipes = currentState.recipes.filter((item:any)=>item.id !== id);
        this.recipeSubject.next({...currentState,updateRecipes})
      })
    )
  }

  likeRecipe(id:any):Observable<any>{
    const headers = this.getHeaders()
    return this.http.put(`${this.baseUrl}/api/recipe/${id}/like`,{headers}).pipe(
      tap((updateRecipe:any)=>{
        const currentState=this.recipeSubject.value;
        const updateRecipes = currentState.recipes.map((item:any)=>item.id===updateRecipe.id?updateRecipe:item)
        this.recipeSubject.next({...currentState,updateRecipes})
      })
    )
  }

}

