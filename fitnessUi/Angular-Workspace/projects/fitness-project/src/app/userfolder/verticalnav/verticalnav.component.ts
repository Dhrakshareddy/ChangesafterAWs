import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-verticalnav',
 
  templateUrl: './verticalnav.component.html',
  styleUrl: './verticalnav.component.css'
})
export class VerticalnavComponent implements OnInit {
  isMenuOpen: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  
}
