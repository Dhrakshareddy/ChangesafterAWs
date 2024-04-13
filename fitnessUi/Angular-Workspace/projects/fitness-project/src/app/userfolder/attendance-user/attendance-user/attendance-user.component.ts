import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../../Dashboard/dash-board/auth.service';
import { AttendanceUser } from '../../../../Models/AttendanceUser.model';
import { AttendanceUserService } from '../../../../Services/AttendanceUserService.service';





@Component({
  selector: 'app-attendance-user',
  templateUrl: './attendance-user.component.html',
  styleUrl: './attendance-user.component.css'
})
export class AttendanceUserComponent  {

  userName: string = ''; 
  attendanceList: AttendanceUser[] = [];

  constructor(private attendanceService: AttendanceUserService,private auth:AuthService) { }


  ngOnInit(): void {
    this.userName=this.auth.getUser().name;

    (this.userName);
  
    this.fetchAttendance();
  }

  fetchAttendance() {
    this.attendanceService.getAttendanceByUserName(this.userName)
      .subscribe((data) => {
      
        this.attendanceList = data;
      });
  }
  
}