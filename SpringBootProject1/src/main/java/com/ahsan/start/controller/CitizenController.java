package com.ahsan.start.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahsan.start.entities.CitizenClass;
import com.ahsan.start.repository.CitizenRepository;
import com.ahsan.start.service.CitizenServiceImpl;

import jakarta.websocket.server.PathParam;

@RestController
public class CitizenController {
	@Autowired
	CitizenServiceImpl citizenServiceImpl;
	@Autowired
	CitizenRepository citizenRepository;
	
	@RequestMapping("/jsp")
	public String callJspPage() {
		return "FrontRequestController";
	}
	
	@GetMapping("/getAllCitizen")
	// @ResponseBody use to get response in json object and we can use consumes and 
	// produces too for getting response in json object
	@ResponseBody()
	public List<CitizenClass> getListOfAllCitizens(){
		List<CitizenClass> listCitizenClasses=citizenServiceImpl.getAllCitizensFromDbUSingJPARepo();
		return listCitizenClasses;
		
	}
	/* Using pagination for limiting result in response and can define number of pages & its size that how many rows from
	 *  table it can fetch
	 *  
	 *  @@RequestParam is used because it allows to write default value of request parameters not like @PathVariable which don't
	 *  allows you to give any default value
	 */
	
	@GetMapping("/citizens")
	public Page<CitizenClass> getCitizens(
	        @RequestParam(name = "page", defaultValue = "0") int page,
	        @RequestParam(name = "size", defaultValue = "10") int size) {
	    return citizenServiceImpl.getAllCitizensFromDbUsingPAginationOfJPARepo(page, size);
	}
	
	@GetMapping(value="/getById/{id}",produces ="application/json")
	public CitizenClass getCitizenById(@PathVariable("id") long id) {
		CitizenClass citizenClass=citizenServiceImpl.getCitizenByIdFromDbUSingJPARepo(id);
		return citizenClass;
	}
	
	@GetMapping(value="/getByAnyColumn/{lastName}/{city}",produces ="application/json")
	public List<CitizenClass> getCitizenByAnyColumn(@PathVariable("lastName") String lastName,@PathVariable("city") String city) {
		List<CitizenClass> citizenClassList=citizenServiceImpl.getByAnyColumns(lastName, city);
		return citizenClassList;
	}
	
	@PostMapping(value="/addCitizen", consumes = "application/json")
	@ResponseBody()
	public CitizenClass addCitizen(@RequestBody CitizenClass citizenClass) {
		citizenServiceImpl.addCitizenInDbUsingJpaRepoc(citizenClass);
		return citizenClass;
	}
	
	@GetMapping(value="/getByTwoArguments/{firstName}/{lastName}",produces ="application/json")
	public CitizenClass getCitizenByCustomMethod(@PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName) {
		CitizenClass citizenClass=citizenRepository.findByFirstNameAndLastName(firstName, lastName);
		return citizenClass;
	}
	
	

}