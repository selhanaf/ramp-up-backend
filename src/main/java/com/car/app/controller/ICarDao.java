package com.car.app.controller;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.List;

import javax.ejb.Remote;
import javax.transaction.Transactional;

import com.car.app.model.Car;


@Remote
public interface ICarDao {

	/**
	 * getting all cars
	 * @return
	 */
	List<Car> findAllCars();

	/**
	 * find car by ID
	 * @param id
	 * @return
	 */
	Car findCarById(String id);

	/**
	 * create a new car
	 * @param car
	 * @return
	 * @throws Exception 
	 */
	Car createCar(Car car) throws Exception;

	/**
	 * update existing car
	 * @param car
	 * @return
	 * @throws Exception
	 */
	Car updateCar(Car car) throws Exception;

	/**
	 * delete a car with it's ID
	 * @param carId
	 * @return
	 */
	boolean deleteCar(String carId);

}