package com.ahsan.start.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahsan.start.entities.CitizenClass;
import com.ahsan.start.repository.CitizenRepository;

import jakarta.transaction.Transactional;

@Service
public class CitizenServiceImpl implements CitizenSevice{
	@Autowired
	private CitizenRepository citizenRepository;

	@Override
	public List<CitizenClass> getAllCitizensFromDbUSingJPARepo() {
		// TODO Auto-generated method stub
		List<CitizenClass> listOfCitizenClasses=new ArrayList<>();
		listOfCitizenClasses=citizenRepository.findAll();
		return listOfCitizenClasses;
	}
	
	public Page<CitizenClass> getAllCitizensFromDbUsingPAginationOfJPARepo(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return citizenRepository.findAll(pageable);
	    }

	@Override
	public CitizenClass getCitizenByIdFromDbUSingJPARepo(long id) {
		// TODO Auto-generated method stub
		Optional<CitizenClass> citizenClassesObj=citizenRepository.findById(id);
		return citizenClassesObj.get();
	}



	@Override
	//@Transactional
	public void addCitizenInDbUsingJpaRepoc(CitizenClass citizenClass) {
		// TODO Auto-generated method stub
		citizenRepository.save(citizenClass);
		
	}

	@Override
	public List<CitizenClass> getByAnyColumns(String lastName, String city) {
		// TODO Auto-generated method stub
		List<CitizenClass> list =citizenRepository.findEach(lastName, city);
		return list;
	}

}
