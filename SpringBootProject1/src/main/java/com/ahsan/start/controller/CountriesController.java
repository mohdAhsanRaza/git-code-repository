package com.ahsan.start.controller;

import java.util.ArrayList;
import java.util.*;
//import java.util.List;

import org.slf4j.helpers.ThreadLocalMapOfStacks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahsan.start.entities.Countries;
import com.ahsan.start.service.CountriesServiceImpl;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;

@RestController
public class CountriesController {
	@Autowired
	CountriesServiceImpl countriesServiceImplObj;
	   // Internal list to accumulate countries and list is used for simple data structures and it allows duplicates
    private List<Countries> countriesList = new ArrayList<>();
    
    // where as map is for complext data structure when there are key value pair and 
    // it does not allow duplicates if you want same key with different it will take last key value only
    private Map<Integer,Countries> mapObjOfCountries=new HashMap<>();

    // Endpoint to add a single country
    @PostMapping(value = "/addCountry/{id}", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<String> addCountry(@RequestBody Countries country,@PathVariable("id") int insertionId) {
        countriesList.add(country); // Add the country to the internal list
        mapObjOfCountries.put(insertionId,country);
        return ResponseEntity.ok("Country added to the list.");
    }
    
    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> addCountryTest(@RequestBody Countries country) {
        countriesList.add(country); // Add the country to the internal list
        
        countriesServiceImplObj.addAllCountriesAtOneCLickUsingJPA(country);
        return ResponseEntity.ok("Country added to the list.");
    }

    // Endpoint to save all accumulated countries to the database
    @PostMapping(value = "/saveCountries")
    @ResponseBody
    @Transactional
    public ResponseEntity<String> saveAllCountries() {
        for (Countries country : countriesList) {
            System.out.println("Name of country is - " + country);
        }
        System.out.println();
        
        // using stream api with forEach loop to iterate maps object 
        mapObjOfCountries.forEach((key, value) -> {
            System.out.println("Added country at this position in map - " + key + ", Country name is " + value);
        });
        
        System.out.println();
        
        // using stream api with filter method to filter out elements of map whose key value is less than 30 
        mapObjOfCountries.entrySet()
        .stream()
        .filter(entry -> entry.getKey() < 30) // Filter entries with value greater than 30
        .forEach(entry -> System.out.println("Key less than 30 is: " + entry.getKey() + ", And its Value: " + entry.getValue()));
        System.out.println();
      //  return ResponseEntity.ok(" All enteries iterated!!! ");

        try {
            for (Countries country : countriesList) {
                countriesServiceImplObj.addAllCountriesAtOneCLickUsingJPA(country);
            }
            countriesList.clear(); // Clear the list after saving
            return ResponseEntity.ok("All countries saved successfully.");
        } catch (DataIntegrityViolationException e) {
            // Handle specific exception without rolling back
            // Log the exception (optional)
            return ResponseEntity.status(400).body("Data integrity violation: " + e.getMessage());
        } catch (Exception e) {
            // Log the exception (optional)
            // Rethrow the exception to trigger rollback
            throw e; // This will cause the transaction to roll back
        }
    }

}
