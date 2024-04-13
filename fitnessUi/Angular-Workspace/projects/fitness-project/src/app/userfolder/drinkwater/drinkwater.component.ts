import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-drinkwater',
 
  templateUrl: './drinkwater.component.html',
  styleUrl: './drinkwater.component.css'
})
export class DrinkwaterComponent {
  waterIntake: number = 0;
  dailyGoal: number = 8;

  constructor(private snackBar: MatSnackBar) {}

  logWater() {
    
    if (this.waterIntake < this.dailyGoal) {
      
      this.showNotification('Remember to drink at least 8 glasses of water!');
    }
    else {
      this.showNotification('Good job')
    }
  }

  private showNotification(message: string): void {
    this.snackBar.open(message, 'Dismiss', {
      duration: 5000, 
    });
  }
}
