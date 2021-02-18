package com.car.app.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ColumnDefault;

import io.swagger.v3.oas.annotations.media.Schema;


@Entity
@Table(name = "car")
@XmlRootElement(name = "Car Model")
public class Car extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5169293798496851483L;
	
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
    @Column(name = "registration", nullable = false)
    private Date registration;
	
	@NotNull
	@Column(name = "toDelete")
	@ColumnDefault("false")
	private boolean toDelete;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="brand_id", nullable=false)
	private Brand brand;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="country_id", nullable=false)
	private Country country;
	

	public boolean isToDelete() {
		return toDelete;
	}

	public void setToDelete(boolean toDelete) {
		this.toDelete = toDelete;
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	@Schema(title = "Date of Car registration", name = "registration", example = "2020-10-10")
	public Date getRegistration() {
		return registration;
	}

	public void setRegistration(Date registration) {
		this.registration = registration;
	}
}
