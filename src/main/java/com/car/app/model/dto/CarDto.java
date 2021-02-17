package com.car.app.model.dto;

import java.util.Date;

import com.car.app.model.Car;

public class CarDto {

	private String id;
	
	private Date createdDate;
	
	private Date updatedDate;

    private Date registration;
	
	private String country;
	
	private String brand;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

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

	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	
	static public CarDto convertCarToDto(Car car) {
		CarDto carDto = new CarDto();
		carDto.setId(car.getId());
		carDto.setCreatedDate(car.getCreatedDate());
		carDto.setUpdatedDate(car.getUpdatedDate());
		carDto.setBrand(car.getBrand().getName());
		carDto.setCountry(car.getCountry().getName());
		carDto.setRegistration(car.getRegistration());
		return carDto;
	}
}
