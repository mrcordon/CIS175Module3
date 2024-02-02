/**
 * @author Mike Cordon - mrcordon@dmacc.edu
 * CIS 175 - Spring 2024
 * Feb 1, 2024
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * A class representing attribute of a dog.  This class is being used as a model in 
 * a JPA database project
 */
@Entity
@Table(name="doggies") 
public class Doggies {
	@Id
	@GeneratedValue
	@Column(name="ID") // primary key
	private int id;
	@Column(name="NAME")
	private String name;
	@Column(name="BREED")
	private String breed;
	@Column(name="COLOR")
	private String color;
	
	// constructors
	// default no args
	public Doggies() {
		super();
	}
	
	// used in creating database rows
	public Doggies(String name, String breed, String color) {
		super();
		this.name = name;
		this.breed = breed;
		this.color = color;
	}

	
	// getters and setters
	// getters
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getBreed() {
		return breed;
	}

	public String getColor() {
		return color;
	}

	
	//setters
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	
	// helper method
	/**
	 * displays detailed information about a dog.
	 * @return
	 */
	public String returnDogDetails() {
		return name + ": " + this.color + " " + this.breed;
	}

		
	
}
