package lms;

import java.io.Serializable;

public abstract class  Person implements Serializable{
	private String name;
	private String cnic;
	private String email;
	private String phoneNo;
	private Address address;
	
	public Person() {
		
	}
	
	public Person(String name, String cnic, String email, String phoneNo, Address address) {
		this.name = name;
		this.cnic = cnic;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
	}
	
	public Person(Person p) {
		this.name=p.name;
		this.phoneNo=p.phoneNo;
		this.cnic=p.cnic;
		this.email=p.email;
		this.address=new Address(p.address);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCnic() {
		return cnic;
	}

	public void setCnic(String cnic) {
		this.cnic = cnic;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public abstract void addMember();
	
	public void display() {
		System.out.println("Member Name: "+this.name);
		System.out.println("CNIC: "+this.cnic);
		System.out.println("Email: "+this.email);
		System.out.println("Phone No.: "+this.phoneNo);
		System.out.println("\nAddress Information: ");
		address.display();	
	}
}
