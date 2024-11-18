import { Component } from '@angular/core';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {MatRadioModule} from '@angular/material/radio';

@Component({
  selector: 'app-update-recipe-form',
  standalone: true,
  imports: [MatButtonModule,FormsModule,MatFormFieldModule,MatRadioModule,MatInputModule],
  templateUrl: './update-recipe-form.component.html',
  styleUrl: './update-recipe-form.component.scss'
})
export class UpdateRecipeFormComponent {

  recipeItem:any={
    title:"burger",
    description:"nice",
    foodType:"veg",
    image:'this.png'
  }

  onSubmit(){
    console.log(this.recipeItem)
  }
}
