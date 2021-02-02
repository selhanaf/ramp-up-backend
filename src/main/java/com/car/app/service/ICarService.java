package com.car.app.service;

import java.util.List;

import com.car.app.model.Car;

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
	public List<Car> getCars();
	
	/**
	 * @param id: if of the car
	 * @return a Car if the car exists, null if not
	 */
	public Car getCar(String id);
	
	
	/**
	 * @param car: object contains information of the car the we want to create
	 * @return the saved car
	 * @throws Exception 
	 */
	public Car createCar(Car car) throws Exception;
	
	
	/**
	 * @param car: contains the new info of an existing car
	 * @return a car with new informations
	 */
	public Car updateCar(Car car);
	
	/**
	 * @param carId: the id of the card we want to delete
	 * @return true if the car deleted, false if not
	 */
	public boolean deleteCar(String carId);
	
}
