import { Component, OnInit } from '@angular/core';

import { AuthService } from '../../../Dashboard/dash-board/auth.service';
import { userclassname } from '../../../../Models/userclassname.model';
import { userclassnameservice } from '../../../../Services/userclassnameservice.service';


@Component({
  selector: 'app-user-class-name',
  templateUrl: './user-class-name.component.html',
  styleUrl: './user-class-name.component.css'
})
export class UserClassNameComponent implements OnInit {
  userName: string = ''; 
  classList: userclassname[] = [];

  constructor(private userService: userclassnameservice,private auth:AuthService) { }

  ngOnInit(): void {
    this.userName=this.auth.getUser().name;
    
    this.fetchClasses();
  }

  fetchClasses() {
    this.userService.getClassesByName(this.userName)
      .subscribe((data: userclassname[]) => {
        
        this.classList = data;
      });
  }

  
 
}
