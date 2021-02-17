package com.car.app.model.dto;

import java.util.Date;

import com.car.app.model.Car;

public class CarDto {

	private String id;
	
	private Date createdDate;
	
	private Date updatedDate;

    private Date registration;
	
	private String CountryName;
	
	private String brandName;
	
	public String getCountryName() {
		return CountryName;
	}

	public void setCountryName(String countryName) {
		CountryName = countryName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
		carDto.setBrandName(car.getBrand().getName());
		carDto.setCountryName(car.getCountry().getName());
		carDto.setRegistration(car.getRegistration());
		return carDto;
	}
}
