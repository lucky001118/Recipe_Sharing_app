import { Component, Input } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import {MatIconModule} from '@angular/material/icon';
import { UpdateRecipeFormComponent } from '../update-recipe-form/update-recipe-form.component';

@Component({
  selector: 'app-recipe-card',
  standalone: true,
  imports: [MatButtonModule,MatCardModule,MatIconModule,MatDialogModule],
  templateUrl: './recipe-card.component.html',
  styleUrl: './recipe-card.component.scss'
})
export class RecipeCardComponent {

  @Input() recipe:any
  constructor(public daialog:MatDialog){}

  handleOpenEditRecipeForm(){
    this.daialog.open(UpdateRecipeFormComponent)
  }
}
