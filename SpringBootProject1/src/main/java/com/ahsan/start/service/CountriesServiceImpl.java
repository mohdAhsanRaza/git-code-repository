package com.ahsan.start.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahsan.start.entities.Countries;
import com.ahsan.start.repository.CountriesRepository;

@Service
public class CountriesServiceImpl implements CountriesService{
	
	@Autowired
	CountriesRepository countriesRepositoryObj;

	@Override
	public void addAllCountriesAtOneCLickUsingJPA(Countries countriesObj) {
		
		countriesRepositoryObj.save(countriesObj);
		
	}

}
