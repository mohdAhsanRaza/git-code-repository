package com.ahsan.start.entities;

import java.sql.Timestamp;

public class StandAloneJava {
	
	private Timestamp currentTimestamp;
	private int primaryKey;
	
	public StandAloneJava() {
		super();
	}
	@Override
	public String toString() {
		return "StandAloneJava [currentTimestamp=" + currentTimestamp + ", primaryKey=" + primaryKey + "]";
	}
	public Timestamp getCurrentTimestamp() {
		return currentTimestamp;
	}
	public void setCurrentTimestamp(Timestamp currentTime) {
		this.currentTimestamp = currentTime;
	}
	public int getPrimaryKey() {
		return primaryKey;
	}
	public void setPrimaryKey(int primaryKey) {
		this.primaryKey = primaryKey;
	}
	
	

}
