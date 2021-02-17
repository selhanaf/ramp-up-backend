package com.car.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "country")
public class Country extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4492287931615153328L;

	@NotNull
	@Column(name = "name", nullable = false, unique=true, length=50)
	private String name;
	

	@OneToMany(mappedBy = "country")
	private Set<Car> cars = new HashSet<Car>();


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Set<Car> getCars() {
		return cars;
	}


	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}
}
