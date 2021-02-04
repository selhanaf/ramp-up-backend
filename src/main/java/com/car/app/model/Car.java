package com.car.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "car")
public class Car extends BaseModel {

	@NotNull
	@Column(name = "brand", nullable = false, unique=false, length=50)
	private String brand;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
    @Column(name = "registration", nullable = false)
    private Date registration;
	
	@NotNull
	@Column(name = "country", nullable = false, unique=false, length=50)
	private String country;

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
	
	
}
