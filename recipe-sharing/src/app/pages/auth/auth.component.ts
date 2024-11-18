import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';
import { CommonModule } from '@angular/common';
import { AuthServiceService } from '../../services/auth/auth-service.service';
// import { Router } from '@angular/router';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [MatButtonModule,MatInputModule,MatFormFieldModule,FormsModule,MatRadioModule,CommonModule,ReactiveFormsModule],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss'
})
export class AuthComponent {

  isRegister=false;

  constructor(public authService:AuthServiceService){}

registerationForm = new FormGroup({
  fullName:new FormControl("",[Validators.required]),
  email:new FormControl("",[Validators.required,Validators.email]),
  password:new FormControl("",[Validators.required,Validators.minLength(6)])
});

loginForm = new FormGroup({
  email:new FormControl("",[Validators.required,Validators.email]),
  password:new FormControl("",[Validators.required])
});

togglePanel(){
  this.isRegister=!this.isRegister;
}

handelRegister(){
  console.log(this.registerationForm.value);
  // console.log(this.loginForm.value);
  this.authService.register(this.registerationForm.value).subscribe({
    next:(response)=>{
      localStorage.setItem("jwt",response.jwt);
      this.authService.getUserProfile().subscribe();
      console.log("signup success ",response);
    }
  })
  
}

handelLogin(){
  console.log(this.loginForm.value);
  this.authService.login(this.loginForm.value).subscribe({
    next:(response)=>{
      localStorage.setItem("jwt",response.jwt);
      this.authService.getUserProfile().subscribe();
      console.log("login success ",response);
    }
  })
}

}
