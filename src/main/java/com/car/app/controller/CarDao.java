package com.car.app.controller;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.model.Car;
import com.car.app.utilities.LogInterceptor;

import static javax.transaction.Transactional.TxType.REQUIRED;

/**
 * @author selhanaf
 * 
 * CarDao is a class used to make interaction with database
 *
 */
@Stateful
@Interceptors(LogInterceptor.class)
public class CarDao {
	
	private static Logger log = LoggerFactory.getLogger(CarDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "auto-car-unit";
	
	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	EntityManager entityManager;
	
	/**
	 * getting all cars
	 * @return List of cars   list of cars
	 */
	public List<Car> findAllCars() {
		List<Car> resultList = entityManager.createQuery("select c from Car c").getResultList();
		log.info("car length = {}", resultList.size());
		return resultList;
	}
	
	/**
	 * find car by ID
	 * @param id          id of the car to get
	 * @return Car
	 */
	public Car findCarById(String id) {
		log.info("find car with id = {}", id);
		Car car = entityManager.find(Car.class, id);
		return car;
	}

	/**
	 * create a new car
	 * @param car            car to create
	 * @return Car		return the created Car
	 */
	@Transactional(REQUIRED)
	public Car createCar(Car car) {
		entityManager.persist(car);
		return car;
		
	}

	/**
	 * update existing car
	 * @param car		Car to update
	 * @return Car		updated car
	 * @throws Exception		throws exception
	 */
	@Transactional(REQUIRED)
	public Car updateCar(Car car) throws Exception {
		log.info("Update the car with the id = {}", car.getId());
		Car findCar = entityManager.find(Car.class, car.getId());
		car.setCreatedDate(findCar.getCreatedDate());
		Car updatedCar = entityManager.merge(car);
		return updatedCar;
		
	}

	/**
	 * delete a car with it's ID
	 * @param carId			the id of car to delete
	 * @return boolean      true if deleted false if not
	 */
	@Transactional(REQUIRED)
	public boolean deleteCar(String carId) {
		log.info("remove car with id ={} ", carId);
		Car car = entityManager.find(Car.class, carId);
		if(car != null) {
			entityManager.remove(car);
			return true;
		} else {
			return false;
		}
	}
}
