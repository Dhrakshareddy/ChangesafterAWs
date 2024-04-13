// loading-spinner.component.ts

import { Component, Input, OnInit } from '@angular/core';
import { LoadingSpinnerAnimation } from '../../../Services/loading-spinner.animations';




@Component({
  selector: 'app-loading-spinner',
  templateUrl: './loading-spinner.component.html',
  styleUrls: ['./loading-spinner.component.css'],
  animations: [LoadingSpinnerAnimation] 
})
export class LoadingSpinnerComponent implements OnInit {
  @Input() showSpinner = false;

  ngOnInit(): void {
    setTimeout(() => {
      this.showSpinner = false;
    }, 3000);
  }
}
