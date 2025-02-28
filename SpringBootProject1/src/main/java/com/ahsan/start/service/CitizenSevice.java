package com.ahsan.start.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ahsan.start.entities.CitizenClass;

public interface CitizenSevice {
	public List<CitizenClass> getAllCitizensFromDbUSingJPARepo();
	public CitizenClass getCitizenByIdFromDbUSingJPARepo(long id);
	public void addCitizenInDbUsingJpaRepoc(CitizenClass citizenClass);
	public List<CitizenClass> getByAnyColumns(String lastName, String city);

}
