package com.car.app.controller;

import java.util.List;

import javax.ejb.Local;

import com.car.app.model.Car;

/**
 * @author selhanaf
 * 
 * the interface contains all methods of Car service
 *
 */
@Local
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
	 * @throws Exception 
	 */
	public Car updateCar(Car car) throws Exception;
	
	/**
	 * @param carId: the id of the card we want to delete
	 * @return true if the car deleted, false if not
	 */
	public boolean deleteCar(String carId);
	
}
