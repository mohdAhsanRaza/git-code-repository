package com.ahsan.start.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ahsan.start.entities.CitizenClass;

@Repository
public interface CitizenRepository extends JpaRepository<CitizenClass,Long>{
	
	// here we are using dsl(dayabase standard language) which provide you ready method support all you have 
	// to pass columns names after "findBy" keyword and it will generate a query for you
	public CitizenClass findByFirstNameAndLastName(String firstName,String lastName);
	
	
	// using HQL(hibernate query language) instead of normal sql to create a query for your variables it can any 
	// not only primary keys or any keys it can normal columns of tables too.
	// hql work on entity classes and fields of class, not columns and tables in the database
	@Query("FROM CitizenClass WHERE lastName=:lName AND city =:cIty ")
	public List<CitizenClass> findEach(@Param("lName") String lastName, @Param("cIty")String City);

}
