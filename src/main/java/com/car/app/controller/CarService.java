package com.car.app.controller;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.client.auth.AuthAPI;
import com.car.app.model.Car;
import com.car.app.model.dto.CarDto;
import com.car.app.utilities.LogInterceptor;
import com.car.app.utilities.PaginationObject;

@Stateless
@Interceptors(LogInterceptor.class)
public class CarService implements ICarService {
	private static Logger log = LoggerFactory.getLogger(CarService.class);

	@Inject
	private CarDao carDao;

	AuthAPI auth = new AuthAPI("dev-04zom-rc.us.auth0.com", "KntuHyTQxJILx19rQSlJSVuoBw2yoynV",
			"oJNX9GE4XEoTdeDxtb-IDRc6NvAbM-2nUmvWg9yMWFyJILwMCbUxPzuZeb6Nex0Z");

	@Override
	public PaginationObject<CarDto> getCars(int size, int page, String sort, String order, String search) {
		log.info("Get all cars");
		return carDao.findAllCars(size, page, sort, order, search);
//		return resultList.stream().map(car -> CarDto.convertCarToDto(car)).collect(Collectors.toList());
	}

	public CarDto getCar(String id) {
		log.info("find car with id = {}", id);
		Car car = carDao.findCarById(id);
		if (car != null) {
			return CarDto.convertCarToDto(car);
		}
		return null;
	}

	public CarDto createCar(Car car) throws Exception {
		try {
			log.info("create new car");
			CarDto carDTO = CarDto.convertCarToDto(carDao.createCar(car));
			return carDTO;
		} catch (Exception e) {
			log.error("error occured", e);
			throw new Exception("Error while creating a new car");
		}

	}

	public CarDto updateCar(Car car) throws Exception {
		try {
			log.info("Update the car with the id = {}", car.getId());
			CarDto updatedCar = CarDto.convertCarToDto(carDao.updateCar(car));
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
