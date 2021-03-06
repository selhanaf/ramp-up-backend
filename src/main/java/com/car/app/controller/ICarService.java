package com.car.app.controller;

import com.car.app.model.Car;
import com.car.app.model.dto.CarDto;
import com.car.app.utilities.PaginationObject;


/**
 * @author selhanaf
 * 
 * the interface contains all methods of Car service
 *
 */
public interface ICarService {

	
	/**
	 * @return list of cars
	 */
	public PaginationObject<CarDto> getCars(int size, int page, String sort, String order, String search);
	
	/**
	 * @param id: if of the car
	 * @return a Car if the car exists, null if not
	 */
	public CarDto getCar(String id);
	
	
	/**
	 * @param car: object contains information of the car the we want to create
	 * @return the saved car
	 * @throws Exception		Throws Exception
	 */
	public CarDto createCar(Car car) throws Exception;
	
	
	/**
	 * @param car: contains the new info of an existing car
	 * @return a car with new informations
	 * @throws Exception 		Throws Exception
	 */
	public CarDto updateCar(Car car) throws Exception;
	
	/**
	 * @param carId: the id of the card we want to delete
	 * @return true if the car deleted, false if not
	 */
	public boolean deleteCar(String carId);
	
}
