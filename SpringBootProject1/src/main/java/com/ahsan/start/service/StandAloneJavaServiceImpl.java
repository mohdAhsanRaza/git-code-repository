package com.ahsan.start.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.ahsan.start.entities.StandAloneJava;

@Service
public class StandAloneJavaServiceImpl implements StandAloneJavaService {
	List<String> listObj=new ArrayList<>();
	// Using Random class to create a new unique primary key value
	Random randomObj=new Random();
	StandAloneJava standAloneJavaObj = new StandAloneJava();
	int primaryKeyVar;
	long timestampVar;
	String combinationOfValues;
	@Override
	public List<String> fetchAllPrimaryKeys() {
		primaryKeyVar=randomObj.nextInt(1000000);
		// Fetching current timesatamp value from system
	    timestampVar=System.currentTimeMillis();
		Timestamp currentTime=new Timestamp(timestampVar);
		// TODO Auto-generated method stub
	
		standAloneJavaObj.setCurrentTimestamp(currentTime);
		standAloneJavaObj.setPrimaryKey(primaryKeyVar);
		combinationOfValues=standAloneJavaObj.getCurrentTimestamp().toString() +"   "+String.valueOf(standAloneJavaObj.getPrimaryKey());
		listObj.add(combinationOfValues);
		return listObj;
	}

}
