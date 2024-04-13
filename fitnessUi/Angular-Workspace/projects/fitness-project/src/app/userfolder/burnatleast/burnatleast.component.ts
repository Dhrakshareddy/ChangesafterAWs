import { Component } from '@angular/core';

@Component({
  selector: 'app-burnatleast',
  templateUrl: './burnatleast.component.html',
  styleUrl: './burnatleast.component.css'
})
export class BurnatleastComponent {
  exerciseType: string = '';
  duration: number = 0;
  caloriesBurned: number | null = null;
  goalCalories: number = 500; 

  calculateCalories() {
    const calorieBurnRatePerMinute = 10; 
    this.caloriesBurned = this.duration * calorieBurnRatePerMinute;
  }

}
