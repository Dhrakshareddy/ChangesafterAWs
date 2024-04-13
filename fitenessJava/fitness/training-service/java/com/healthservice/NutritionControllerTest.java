package com.healthservice;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
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
import com.healthservice.bean.UserBean;
import com.healthservice.controller.NutritionController;
import com.healthservice.entity.Nutrition;
import com.healthservice.service.UserNutritionService;

@ExtendWith(MockitoExtension.class)
public class NutritionControllerTest {

	@Mock
	private UserNutritionService userNutritionService;

	@InjectMocks
	private NutritionController nutritionController;

	private Nutrition nutrition;

	@BeforeEach
	public void setUp() {
		nutrition = new Nutrition();
		nutrition.setId(1);
		nutrition.setDescription("Sample Nutrition");
//
//		// Mock the behavior of userNutritionService methods as needed
//		when(userNutritionService.saveNewNutrition(any())).thenReturn(nutrition);
//		when(userNutritionService.getById(anyInt())).thenReturn(new ResponseDto(/* mock data */));
//		when(userNutritionService.getAllNutrition()).thenReturn(new ArrayList<>());
//		when(userNutritionService.updateNutrition(anyInt(), any())).thenReturn(nutrition);
//		when(userNutritionService.getByUsername(anyString())).thenReturn(new ArrayList<>());
//		when(userNutritionService.getUserBean()).thenReturn(new ArrayList<>());
	}

	 @Test
	    public void testSaveNewNutrition() {
	        // Given
	        Nutrition nutritionToSave = new Nutrition();  // Create a sample nutrition object
	        when(userNutritionService.saveNewNutrition(any(Nutrition.class))).thenReturn(nutritionToSave);

	        // When
	        ResponseEntity<Nutrition> response = nutritionController.saveNewNutrition(nutritionToSave);

	        // Then
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	        assertEquals(nutritionToSave, response.getBody());

	        // Verify that the saveNewNutrition method of the userService is called with the expected argument
	        verify(userNutritionService).saveNewNutrition(eq(nutritionToSave));
	    }



	@Test
	public void testGetById() {
		ResponseEntity<ResponseDto> response = nutritionController.getById(1);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		// Add assertions for the expected response body
	}

	@Test
	public void testGetAll() {
		List<Nutrition> nutritionList = nutritionController.getAll();

		assertEquals(new ArrayList<>(), nutritionList);
	}
	 @Test
	    public void testUpdateNutrition() {
	        // Given
	        Integer nutritionId = 1;
	        Nutrition updatedNutrition = new Nutrition();  // Create a sample updated nutrition object
	        when(userNutritionService.updateNutrition(eq(nutritionId), any(Nutrition.class))).thenReturn(updatedNutrition);

	        // When
	        ResponseEntity<?> response = nutritionController.updateNutrition(nutritionId, updatedNutrition);

	        // Then
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(updatedNutrition, response.getBody());

	        // Verify that the updateNutrition method of the userService is called with the expected arguments
	        verify(userNutritionService).updateNutrition(eq(nutritionId), eq(updatedNutrition));
	    }
	@Test
	public void testDeleteNutrition() {
		// No return value as it is a void method
		nutritionController.deleteNutrition(1);

		// Add assertions or verifications if needed
	}

	@Test
	public void testGetAllUsers() {
		ResponseEntity<List<UserBean>> response = nutritionController.getAllUsers();

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(new ArrayList<>(), response.getBody());
	}

	@Test
	public void testGetByUsername() {
		ResponseEntity<List<Nutrition>> response = nutritionController.getByUsername("username");

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(new ArrayList<>(), response.getBody());
	}
}

