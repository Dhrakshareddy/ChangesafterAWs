import { Component, OnInit } from '@angular/core';
import { fetchUser } from '../../../Models/fetchUser.model';
import { UserFetchService } from '../../../Services/UserFetch.service';


@Component({
  selector: 'app-fetchuser',
  templateUrl: './fetchuser.component.html',
  styleUrl: './fetchuser.component.css'
})
export class FetchuserComponent {
  userName: string = ''; 
  userList: fetchUser[] = [];

  constructor(private userFetchService: UserFetchService) { }

  ngOnInit(): void {
    this.fetchUser();
  }

  fetchUser() {
    this.userFetchService.getUserByName(this.userName)
      .subscribe((data: fetchUser[]) => {
        (data); 
        this.userList=data;
      });
  }
}
