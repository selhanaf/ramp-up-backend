package com.car.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Table(name = "car")
@XmlRootElement(name = "Car Model")
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

	@Schema(title = "Brand Car", name = "brand", example = "BMW")
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Schema(title = "Date of Car registration", name = "registration", example = "2020-10-10")
	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}

	@Schema(title = "Country of Car registration", name = "country", example = "Morocco")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	
}
