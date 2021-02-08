package com.car.app.resource;

import javax.ws.rs.core.Response;

import com.car.app.model.Car;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;


/**
 * @author selhanaf
 *	interface for mapping all the APIs with swagger documentation
 */
public interface ICarResource {

	/**
	 * getting all cars
	 * @return Response
	 */
	@Operation( summary = "Fetch All cars")
	public Response getCars();

	/**
	 * getting a car by it's ID
	 * @param id
	 * @return Response
	 */
	@Operation( summary = "Fetch car by Id")
	public Response getCar(@Parameter(description  = "Car Id", required = true) String id);
	
	/**
	 * create a new car
	 * @param car
	 * @return
	 * @throws Exception
	 */
	@Operation( summary = "Create new car")
	public Response createCar(@Parameter(description  = "Car Object", required = true) Car car) throws Exception;
	
	/**
	 * update existing car
	 * @param car
	 * @return
	 * @throws Exception
	 */
	@Operation( summary = "Update existing car")
	public Response updateCar(@Parameter(description  = "Car Object", required = true) Car car) throws Exception;
	
	/**
	 * delete an existing cars
	 * @param id
	 * @return
	 */
	@Operation( summary = "Fetch car by Id")
	public Response deleteCar(@Parameter(description  = "Car Id", required = true) String id);

}