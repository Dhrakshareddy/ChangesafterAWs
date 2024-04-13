import { Component, OnInit } from '@angular/core';
import { Equipment } from '../model/equipment.model';
import { EquipmentService } from '../service/eqipment.service';

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrl: './equipment-list.component.css'
})
export class EquipmentListComponent implements OnInit {
  equipment: Equipment[] = [];
  constructor(private equipmentService: EquipmentService) { }

  ngOnInit(): void {

    this.getEquipment();

  }
  getEquipment() {
    this.equipmentService.getEquipment().subscribe((equipment: Equipment[]) => {
      (equipment);
      this.equipment = equipment
    });
  }
  deleteEquipment(id: number): void {
    (id);

    this.equipmentService.deleteEquipment(id).subscribe((res) => {

      this.getEquipment();
    });
  }
}



