package com.car.app.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.dao.CarDao;
import com.car.app.model.Car;

public class CarService implements ICarService {
	private static Logger log = LoggerFactory.getLogger(CarService.class);
	private CarDao carDao = new CarDao();
	
	public List<Car> getCars() {
		log.info("ENTER : getCars");
		log.info("Get all cars");
		List<Car> resultList = carDao.findAllCars();
		log.info("car length = {}", resultList.size());
		log.info("EXIT : getCars");
		return resultList;
	}

	public Car getCar(String id) {
		log.info("ENTER : getCar");
		log.info("find car with id = {}", id);
		Car car = carDao.findCarById(id);
		log.info("EXIT : getCar");
		return car;
	}

	public Car createCar(Car car) throws Exception {
		log.info("ENTER : createCar");
		try {
			log.info("create new car");
			car = carDao.createCar(car);
			log.info("EXIT : createCar");
			return car;
		} catch (Exception e) {
			log.error("error occured", e);
			throw new Exception("Error while creating a new car");
		}
		
	}

	public Car updateCar(Car car) throws Exception {
		log.info("ENTER : updateCar");
		try {
			log.info("Update the car with the id = {}", car.getId());
			Car updatedCar = carDao.updateCar(car);
			log.info("EXIT : updateCar");
			return updatedCar;
			
		} catch (Exception e) {
			log.error("error occured", e);
			throw new Exception("Error while updating a car");
		}
		
	}

	public boolean deleteCar(String carId) {
		log.info("ENTER : deleteCar");
		log.info("remove car with id ={} ", carId);
		return carDao.deleteCar(carId);
	}
	
}
