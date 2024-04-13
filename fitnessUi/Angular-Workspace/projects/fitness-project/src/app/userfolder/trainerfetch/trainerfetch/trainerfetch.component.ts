import { Component } from '@angular/core';
import { fetchTrainer } from '../../../../Models/fetchTrainer.model';
import { TrainerFetchService } from '../../../../Services/TrainerFetch.service';


@Component({
  selector: 'app-trainerfetch',

  templateUrl: './trainerfetch.component.html',
  styleUrl: './trainerfetch.component.css'
})
export class TrainerfetchComponent {
  trainerName: string = ''; 
 
  trainers: fetchTrainer[] = [];

  constructor(private trainerFetchService: TrainerFetchService) { }
  ngOnInit(): void {
    this.fetchTrainer();
  }

  fetchTrainer() {
    this.trainerFetchService.getTrainerByName(this.trainerName)
      .subscribe((data: fetchTrainer[]) => {
        (data); 
        this.trainers=data;
      });
  }
}
