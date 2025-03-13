package com.ahsan.start.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahsan.start.entities.StandAloneJava;
import com.ahsan.start.service.StandAloneJavaServiceImpl;

@RestController
public class StandAloneJavaController {
	
	// using in memory database which is a list to save timestamp and primary keys
	List<String> listOfPrimaryKeys=new ArrayList<>();
	
	@Autowired
	StandAloneJavaServiceImpl standAloneJavaServiceImplObj; 
		
	@GetMapping(value="/getPrimaryKeys")
	public void getUniquePrimaryKeys(){
		listOfPrimaryKeys=standAloneJavaServiceImplObj.fetchAllPrimaryKeys();
		System.out.println("Current Timestamp and their Unique Primary Key is : \n "+ listOfPrimaryKeys);
		// removing all elements of list after showing result
		listOfPrimaryKeys.clear();
	}
	

}
