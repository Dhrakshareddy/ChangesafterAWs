// src/app/doctor-list/doctor-list.component.ts
import { Component, OnInit } from '@angular/core';
import { DoctorService } from '../service/doctor.service';
import { Doctor } from '../model/doctor.model';

@Component({
  selector: 'app-doctor-list',
  templateUrl: './doctor-list.component.html',
  styleUrls: ['./doctor-list.component.css']
})
export class DoctorListComponent implements OnInit {
  doctors: Doctor[] = [];

  constructor(private doctorService: DoctorService) { }

  ngOnInit(): void {
    this.getDoctors()
  }

  getDoctors() {
    this.doctorService.getDoctors().subscribe((doctors: Doctor[]) => {
     
      this.doctors = doctors;
    });
  }
  deleteDoctor(id: number): void {
    this.doctorService.deleteDoctor(id).subscribe((res) => {

      this.getDoctors();
    });
  }


}