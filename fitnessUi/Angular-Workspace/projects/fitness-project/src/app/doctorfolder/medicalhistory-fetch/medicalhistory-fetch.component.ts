import { Component, OnInit } from '@angular/core';

import { MedicalHistoryDisplayClassService } from '../../../Services/medical-history-display';
import { MedicalHistory } from '../../../Models/medical-history.model';
import { AuthService } from '../../Dashboard/dash-board/auth.service';



@Component({
  selector: 'app-medicalhistory-fetch',
  templateUrl: './medicalhistory-fetch.component.html',
  styleUrl: './medicalhistory-fetch.component.css'
})
export class MedicalhistoryFetchComponent  
{
  userName: string = ''; 
medicalHistory: MedicalHistory[] = [];
constructor(private medicalHistoryService: MedicalHistoryDisplayClassService,private auth:AuthService) { }

ngOnInit(): void {
this.userName=history.state.name;
console.log(this.userName)
  this.fetchMedicalHistoryByName();
}
fetchMedicalHistoryByName() {

  this.medicalHistoryService.getMedicalHistory(this.userName).subscribe((res)=>{
    
    this.medicalHistory=res;

    console.log(this.medicalHistory);
    
  })
}
}

