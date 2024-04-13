package com.healthservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.healthservice.bean.ResponseDto;
import com.healthservice.controller.MedicalHistoryController;
import com.healthservice.entity.MedicalHistory;
import com.healthservice.service.MedicalHistoryService;

@ExtendWith(MockitoExtension.class)
public class MedicalHistoryControllerTest {

    @Mock
    private MedicalHistoryService medicalHistoryService;

    @InjectMocks
    private MedicalHistoryController medicalHistoryController;

    private MedicalHistory medicalHistory;

    @BeforeEach
    public void setUp() {
        medicalHistory = new MedicalHistory();
        medicalHistory.setId(1L);
        medicalHistory.setDescription("Sample Description");
    }

    @Test
    public void testSaveNewMedicalHistory() {
        when(medicalHistoryService.saveNewMedicalHistory(any(MedicalHistory.class))).thenReturn(medicalHistory);

        ResponseEntity<MedicalHistory> response = medicalHistoryController.saveNewMedicalHistory(medicalHistory);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(medicalHistory, response.getBody());
    }

    @Test
    public void testGetById() {
        when(medicalHistoryService.getById(1L)).thenReturn(new ResponseDto(/* mock data */));

        ResponseEntity<ResponseDto> response = medicalHistoryController.getById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        // Add assertions for the expected response body
    }

    @Test
    public void testGetAll() {
        List<MedicalHistory> medicalHistoryList = new ArrayList<>();
        medicalHistoryList.add(medicalHistory);

        when(medicalHistoryService.getAllMedicalHistory()).thenReturn(medicalHistoryList);

        List<MedicalHistory> response = medicalHistoryController.getAll();

        assertEquals(medicalHistoryList, response);
    }

    @Test
    public void testUpdateMedicalHistory() {
        when(medicalHistoryService.updateMedicalHistory(any(MedicalHistory.class))).thenReturn(medicalHistory);

        MedicalHistory updatedMedicalHistory = medicalHistoryController.updateMedicalHistory(medicalHistory);

        assertEquals(medicalHistory, updatedMedicalHistory);
    }

    @Test
    public void testDeleteMedicalHistory() {
        // No return value as it is a void method
        medicalHistoryController.deleteMedicalHistory(1L);

        // Add assertions or verifications if needed
    }

    @Test
    public void testGetMedicalHistoryByName() {
        List<MedicalHistory> medicalHistoryList = new ArrayList<>();
        medicalHistoryList.add(medicalHistory);

        when(medicalHistoryService.getMedicalHistoryByName("username")).thenReturn(medicalHistoryList);

        ResponseEntity<List<MedicalHistory>> response = medicalHistoryController.getMedicalHistoryByName("username");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicalHistoryList, response.getBody());
    }

}
