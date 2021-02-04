package com.car.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "car")
@ApiModel("Car Model")
public class Car extends BaseModel {

	@Column(name = "brand", nullable = false, unique=false, length=50)
	private String brand;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration", nullable = false)
    private Date registration;
	
	@Column(name = "country", nullable = false, unique=false, length=50)
	private String country;

	@ApiModelProperty(value = "Brand Car", name = "brand",  dataType = "String", example = "BMW")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@ApiModelProperty(value = "Date of Car registration", name = "registration",  dataType = "date", example = "2020-10-10")
	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	@ApiModelProperty(value = "Country of Car registration",name = "country",  dataType = "String", example = "Morocco")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
