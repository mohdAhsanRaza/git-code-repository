package com.ahsan.start.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ahsan.start.entities.Countries;

@Repository
public interface CountriesRepository extends JpaRepository<Countries, Long> {

}
