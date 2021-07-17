package lms;

import java.io.Serializable;

public class Address implements Serializable {
	private int house;
	private int street;
	private String sector;
	private String city;
	private String country;
	
	public Address(int house, int street, String sector, String city, String country) {
		super();
		this.house = house;
		this.street = street;
		this.sector = sector;
		this.city = city;
		this.country = country;
	}
	
	public Address(Address a) {
		this.house=a.house;
		this.street=a.street;
		this.sector=a.sector;
		this.city=a.city;
		this.country=a.country;
	}

	public int getHouse() {
		return house;
	}

	public void setHouse(int house) {
		this.house = house;
	}

	public int getStreet() {
		return street;
	}

	public void setStreet(int street) {
		this.street = street;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public void display() {
		System.out.println("House: "+this.house);
		System.out.println("Street: "+this.street);
		System.out.println("Sector: "+this.sector);
		System.out.println("City: "+this.city);
		System.out.println("Country: "+this.country);
	}
}
