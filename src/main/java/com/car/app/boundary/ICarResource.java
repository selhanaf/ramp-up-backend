package com.car.app.boundary;

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
	public Response getCars(int size, int page, String sort, String order, String search);
	
	/**
	 * getting a car by it's ID
	 * @param id      id of the car
	 * @return Response
	 */
	@Operation( summary = "Fetch car by Id")
	public Response getCar(@Parameter(description  = "Car Id", required = true) String id);
	
	/**
	 * create a new car
	 * @param car    the car to create
	 * @return Response
	 * @throws Exception    throws exception
	 */
	@Operation( summary = "Create new car")
	public Response createCar(@Parameter(description  = "Car Object", required = true) Car car) throws Exception;
	
	/**
	 * update existing car
	 * @param car       car to update
	 * @return Response
	 * @throws Exception    throws exception
	 */
	@Operation( summary = "Update existing car")
	public Response updateCar(@Parameter(description  = "Car Object", required = true) Car car) throws Exception;
	
	/**
	 * delete an existing cars
	 * @param id    if of the car to delete
	 * @return Response
	 */
	@Operation( summary = "Fetch car by Id")
	public Response deleteCar(@Parameter(description  = "Car Id", required = true) String id);

}