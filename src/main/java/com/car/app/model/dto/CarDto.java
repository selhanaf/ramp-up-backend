package com.car.app.model.dto;

import java.util.Date;

import com.car.app.model.Car;

public class CarDto {

	private String id;
	
	private Date createdDate;
	
	private Date updatedDate;

	private String brand;
	
    private Date registration;
	
	private String country;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
	static public CarDto convertCarToDto(Car car) {
		CarDto carDto = new CarDto();
		carDto.setId(car.getId());
		carDto.setCreatedDate(car.getCreatedDate());
		carDto.setUpdatedDate(car.getUpdatedDate());
		carDto.setBrand(car.getBrand());
		carDto.setCountry(car.getCountry());
		carDto.setRegistration(car.getRegistration());
		return carDto;
	}
}
