package com.car.app.resource;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.car.app.model.Car;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


public interface ICarResource {

	@ApiOperation(produces = "application/json", value = "Fetch All cars", httpMethod = "GET", response = Response.class)
	public Response getCars();

	@ApiOperation(produces = "application/json", value = "Fetch car by Id")
	public Response getCar(@ApiParam(value = "Car Id", required = true) String id);
	
	@ApiOperation(produces = "application/json", value = "Create new car", httpMethod = "POST", response = Response.class)
	public Response createCar(@ApiParam(value = "Car Object", required = true) Car car) throws Exception;
	
	@ApiOperation(produces = "application/json", value = "Update existing car", httpMethod = "PUT", response = Response.class)
	public Response updateCar(@ApiParam(value = "Car Object", required = true) Car car) throws Exception;
	
	@ApiOperation(produces = "application/json", value = "Fetch car by Id", httpMethod = "DELETE", response = Response.class)
	public Response deleteCar(@ApiParam(value = "Car Id", required = true) String id);

}