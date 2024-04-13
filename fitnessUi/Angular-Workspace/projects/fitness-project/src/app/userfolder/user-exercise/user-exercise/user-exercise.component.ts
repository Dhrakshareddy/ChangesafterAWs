import { Component } from '@angular/core';
import { Exercise } from './userexercise.model';
import { ExerciseService } from './userexercise.service';
import { AuthService } from '../../../Dashboard/dash-board/auth.service';


@Component({
  selector: 'app-user-exercise',
  templateUrl: './user-exercise.component.html',
  styleUrl: './user-exercise.component.css'
})
export class UserExerciseComponent {
  userName: string = ''; 
  exercise: Exercise[] = [];
  constructor(private exerciseService: ExerciseService,private auth:AuthService) { }

  ngOnInit(): void {
    this.userName=this.auth.getUser().name;
    
    this.fetchExerciseByName();
  }
  fetchExerciseByName() {

    this.exerciseService.getExerciseByName(this.userName).subscribe((res)=>{

     
      this.exercise=res;
     
    })
  }
}



