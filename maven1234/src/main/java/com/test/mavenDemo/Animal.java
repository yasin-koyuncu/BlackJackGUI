package com.test.mavenDemo;

public class Animal {

	private int id=-1 ;
	private String name;
	
	public Animal(String name) {
		
		
		this.name = name;
		id++;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	
	
	
	
	
}
