import { Component } from '@angular/core';

import { AuthService } from '../../Dashboard/dash-board/auth.service';
import { NutritionUser } from '../../../Models/NutritionUser.model';
import { NutritionUserService } from '../../../Services/NutritionUserService.service';

@Component({
  selector: 'app-fetchnutrition',
  templateUrl: './fetchnutrition.component.html',
  styleUrls: ['./fetchnutrition.component.css']
})
export class FetchnutritionComponent {
  username: string = '';
  nutritionList: NutritionUser[] = [];

  constructor(private nutritionService: NutritionUserService,private auth:AuthService) { }

  ngOnInit(): void {
    this.username=this.auth.getUser().name;
    
    this.fetchNutrition();
  }

  fetchNutrition() {
   
    this.nutritionService.getNutritionByName(this.username)
      .subscribe((data: NutritionUser) => {
       
       
        this.nutritionList = [data];
      });
  }
}