// src/app/user/user.model.ts
export interface User {
  userId?: number;
  name: string;
  password: string;
  dateofBirth: Date;
  email: string;
  contactNumber: number;
  role: string;
  trainerCode: string;
  doctorCode: string;
}


