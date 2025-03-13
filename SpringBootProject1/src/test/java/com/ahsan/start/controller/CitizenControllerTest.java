package com.ahsan.start.controller;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ahsan.start.entities.CitizenClass;
import com.ahsan.start.repository.CitizenRepository;
import com.ahsan.start.service.CitizenServiceImpl;

public class CitizenControllerTest {

    @Mock
    public CitizenController citizenControllerMockObj;

    @InjectMocks
    CitizenServiceImpl citizenServiceImplMockObj;// Use @Mock
    
    @Mock
    CitizenRepository citizenRepositoryMockObj;
    
    CitizenClass mockCitizen;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        // No need to mock Connection and DriverManager for this test
        
     // Create a mock CitizenClass object
        mockCitizen = Mockito.mock(CitizenClass.class);
        Mockito.when(mockCitizen.getId()).thenReturn((long) 1); 
        Mockito.when(mockCitizen.getAddress()).thenReturn("ABC"); 
        Mockito.when(mockCitizen.getCity()).thenReturn("DEF");
        Mockito.when(mockCitizen.getFirstName()).thenReturn("JOHN");
        Mockito.when(mockCitizen.getLastName()).thenReturn("PARKER");
      //  Mockito.when(mockCitizen.getIdentityCards()).thenReturn(anyList());
        		
        
        // Prepare mock data
   
    }

    @Test
    public void getAllListTest() {
        // Prepare mock data
        List<CitizenClass> mockList = new ArrayList<>();
        mockList.add(mockCitizen);

        // Mock the behavior of the service method
        Mockito.when(citizenServiceImplMockObj.getAllCitizensFromDbUSingJPARepo()).thenReturn(mockList);

        // Call the method under test
        List<CitizenClass> list = citizenServiceImplMockObj.getAllCitizensFromDbUSingJPARepo();

        // Assertions
        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(1, list.size()); // Check if the size is as expected
    }
    
    
//    @Test
//    public void getAllListTest() {
//        // Prepare mock data
//        List<CitizenClass> mockList = new ArrayList<>();
//        mockList.add(mockCitizen);
//
//        // Mock the behavior of the service method
//        Mockito.when(citizenServiceImplMockObj.getAllCitizensFromDbUSingJPARepo()).thenReturn(mockList);
//
//        // Call the method under test
//        List<CitizenClass> list = citizenServiceImplMockObj.getAllCitizensFromDbUSingJPARepo();
//
//        // Assertions
//        Assertions.assertFalse(list.isEmpty());
//        Assertions.assertEquals(1, list.size()); // Check if the size is as expected
//    }
//    
//    
//    @Test
//    public void getAllListTest() {
//        // Prepare mock data
//        List<CitizenClass> mockList = new ArrayList<>();
//        mockList.add(mockCitizen);
//
//        // Mock the behavior of the service method
//        Mockito.when(citizenServiceImplMockObj.getAllCitizensFromDbUSingJPARepo()).thenReturn(mockList);
//
//        // Call the method under test
//        List<CitizenClass> list = citizenServiceImplMockObj.getAllCitizensFromDbUSingJPARepo();
//
//        // Assertions
//        Assertions.assertFalse(list.isEmpty());
//        Assertions.assertEquals(1, list.size()); // Check if the size is as expected
//    }
    
    
    @Test
    public void getCitizenByAnyColumnTest() {
        // Prepare mock data
        List<CitizenClass> mockList = new ArrayList<>();
        mockList.add(mockCitizen);

        // Mock the behavior of the service method
        Mockito.when(citizenServiceImplMockObj.getCitizenByIdFromDbUSingJPARepo(1L)).thenReturn(mockCitizen);

        // Call the method under test
        CitizenClass citizenClassObj = citizenServiceImplMockObj.getCitizenByIdFromDbUSingJPARepo(1L);

        // Assertions
        Assertions.assertEquals(citizenClassObj.getFirstName(),"JOHN"); // Check if the size is as expected
    }
    
    
    @Test
    public void addCitizenTest() {
   
		// Mock the behavior of the repository's save method
        Mockito.when(citizenRepositoryMockObj.save(mockCitizen)).thenReturn(mockCitizen);
        
        citizenServiceImplMockObj.addCitizenInDbUsingJpaRepoc(mockCitizen);
        
        // Verify that the save method was called with the correct parameter
        Mockito.verify(citizenRepositoryMockObj).save(mockCitizen);

    }
    
}