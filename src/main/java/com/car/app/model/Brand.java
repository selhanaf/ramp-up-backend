package com.car.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "brand")
public class Brand extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4352719736830405766L;
	
	
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "brand")
	private Set<Car> cars = new HashSet<Car>();

	public Set<Car> getCars() {
		return cars;
	}


	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
}
