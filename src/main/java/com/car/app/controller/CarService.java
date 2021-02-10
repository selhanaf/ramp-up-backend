package com.car.app.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.model.Car;
import com.car.app.utilities.LogInterceptor;

@Stateless
@Interceptors(LogInterceptor.class)
public class CarService implements ICarService {
	private static Logger log = LoggerFactory.getLogger(CarService.class);
	
	@Inject
	private CarDao carDao;
	
	
	public List<Car> getCars() {
		log.info("Get all cars");
		List<Car> resultList = carDao.findAllCars();
		log.info("car length = {}", resultList.size());
		return resultList;
	}

	public Car getCar(String id) {
		log.info("find car with id = {}", id);
		Car car = carDao.findCarById(id);
		return car;
	}

	public Car createCar(Car car) throws Exception {
		try {
			log.info("create new car");
			car = carDao.createCar(car);
			return car;
		} catch (Exception e) {
			log.error("error occured", e);
			throw new Exception("Error while creating a new car");
		}
		
	}

	public Car updateCar(Car car) throws Exception {
		try {
			log.info("Update the car with the id = {}", car.getId());
			Car updatedCar = carDao.updateCar(car);
			return updatedCar;
			
		} catch (Exception e) {
			log.error("error occured", e);
			throw new Exception("Error while updating a car");
		}
		
	}

	public boolean deleteCar(String carId) {
		log.info("remove car with id ={} ", carId);
		return carDao.deleteCar(carId);
	}
	
}
