package com.mobicule.utilities;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class ProducerUserBean {

	private int id;
	private String firstName;
	private String lastName;
	private int age;
	private long phoneNumber;
	private String[] cities;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String imageString;
	private String imageLocation;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getImageString() {
		return imageString;
	}
	public void setImageString(String imageString) {
		this.imageString = imageString;
	}
	public String getImageLocation() {
		return imageLocation;
	}
	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}
	
	
	
	
	public String[] getCities() {
		return cities;
	}
	public void setCities(String[] cities) {
		this.cities = cities;
	}
	@Override
	public String toString() {
		return "UserBean [id=" + id + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", age=" + age + ", phoneNumber="
				+ phoneNumber + ", cities=" + Arrays.toString(cities)
				+ ", imageString=" + imageString + ", imageLocation="
				+ imageLocation + "]";
	}
	
	
	
}
