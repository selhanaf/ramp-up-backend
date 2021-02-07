package com.car.app.resource;

import javax.ws.rs.core.Response;

import com.car.app.model.Car;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


public interface ICarResource {

	@Operation( summary = "Fetch All cars")
	public Response getCars();

	@Operation( summary = "Fetch car by Id")
	public Response getCar(@Parameter(description  = "Car Id", required = true) String id);
	
	@Operation( summary = "Create new car")
	public Response createCar(@Parameter(description  = "Car Object", required = true) Car car) throws Exception;
	
	@Operation( summary = "Update existing car")
	public Response updateCar(@Parameter(description  = "Car Object", required = true) Car car) throws Exception;
	
	@Operation( summary = "Fetch car by Id")
	public Response deleteCar(@Parameter(description  = "Car Id", required = true) String id);

}