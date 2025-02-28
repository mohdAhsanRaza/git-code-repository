package com.ahsan.start.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "citizens") // Ensure this matches the table name in your database
public class CitizenClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generate ID
    @Column(name = "CitizenID") // Column name in the database
    private long id;

    @Column(name = "FirstName", nullable = false) // Mark as not nullable if required
    private String firstName;

    @Column(name = "LastName", nullable = false) // Mark as not nullable if required
    private String lastName;

    @Column(name = "City")
    private String city;

    @Column(name = "Address")
    private String address;

    @OneToMany(mappedBy = "citizenClass",cascade = CascadeType.ALL)
    @JsonIgnore //used for neglecting rows of table of child class or relationship class in json response
    private List<IdentityCards> identityCards;
    
   

	public List<IdentityCards> getIdentityCards() {
		return identityCards;
	}

	public void setIdentityCards(List<IdentityCards> identityCards) {
		this.identityCards = identityCards;
	}

	// Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "CitizenClass [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", city=" + city
                + ", address=" + address + "]";
    }
}