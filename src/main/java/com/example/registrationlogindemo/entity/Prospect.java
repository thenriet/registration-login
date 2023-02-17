package com.example.registrationlogindemo.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@jakarta.persistence.Entity
public class Prospect {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	private String name;
	private String siret;
	private String address;
	private String zip;
	private String city;
	
	public Prospect() {
		
	}
	
	public Prospect(String name, String siret, String address, String zip, String city) {
		super();
		this.name = name;
		this.siret = siret;
		this.address = address;
		this.zip = zip;
		this.city = city;
	}
	
	@Override
	public String toString() {
		return (this.id+ " "+ this.name + " " + this.siret + " " + this.address + " " + this.zip + " " + this.city);
		
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
}
