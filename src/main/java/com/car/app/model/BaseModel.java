package com.car.app.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.v3.oas.annotations.media.Schema;



/**
 * @author selhanaf
 * 
 * The class of all models that can we add to this project
 *
 */
@MappedSuperclass
public abstract class BaseModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1399495828692808773L;
	
	@Id
	@GenericGenerator(name = "UUID", strategy = "uuid2")
	@GeneratedValue(generator = "UUID")
	@NotNull
	@Column(name = "id")
	private String id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;
	
	@PrePersist
    protected void onCreate() {
		this.updatedDate = new Date();
		this.createdDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
    	this.updatedDate = new Date();
    	this.createdDate = new Date();
    }

    @Schema(required = true, example = "14a3036c-6c47-490e-87e0-eca768310abd")
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}


}
