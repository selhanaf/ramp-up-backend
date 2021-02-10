package com.car.app.controller;

import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.model.Car;

import static javax.transaction.Transactional.TxType.REQUIRED;

/**
 * @author selhanaf
 * 
 * CarDao is a class used to make interaction with database
 *
 */
@Stateful
public class CarDao {
	
	private static Logger log = LoggerFactory.getLogger(CarDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "auto-car-unit";
	
	@PersistenceContext(unitName = PERSISTENCE_UNIT_NAME)
	EntityManager entityManager;
	
	/**
	 * getting all cars
	 * @return
	 */
	public List<Car> findAllCars() {
		log.info("ENTER : findAllCars");
		List<Car> resultList = entityManager.createQuery("select c from Car c").getResultList();
		log.info("car length = {}", resultList.size());
		log.info("EXIT : findAllCars");
		return resultList;
	}
	
	/**
	 * find car by ID
	 * @param id
	 * @return
	 */
	public Car findCarById(String id) {
		log.info("ENTER : findCarById");
		log.info("find car with id = {}", id);
		Car car = entityManager.find(Car.class, id);
		log.info("EXIT : findCarById");
		return car;
	}

	/**
	 * create a new car
	 * @param car
	 * @return
	 * @throws Exception 
	 */
	@Transactional(REQUIRED)
	public Car createCar(Car car) {
		log.info("ENTER : createCar");
		entityManager.persist(car);
		log.info("EXIT : createCar");
		return car;
		
	}

	/**
	 * update existing car
	 * @param car
	 * @return
	 * @throws Exception
	 */
	@Transactional(REQUIRED)
	public Car updateCar(Car car) throws Exception {
		log.info("ENTER : updateCar");
		log.info("Update the car with the id = {}", car.getId());
		Car findCar = entityManager.find(Car.class, car.getId());
		car.setCreatedDate(findCar.getCreatedDate());
		Car updatedCar = entityManager.merge(car);
		log.info("EXIT : updateCar");
		return updatedCar;
		
	}

	/**
	 * delete a car with it's ID
	 * @param carId
	 * @return
	 */
	@Transactional(REQUIRED)
	public boolean deleteCar(String carId) {
		log.info("ENTER : deleteCar");
		log.info("remove car with id ={} ", carId);
		entityManager.getTransaction().begin();
		Car car = entityManager.find(Car.class, carId);
		if(car != null) {
			entityManager.remove(car);
			entityManager.getTransaction().commit();
			entityManager.clear();
			log.info("EXIT : deleteCar");
			return true;
		} else {
			entityManager.getTransaction().commit();
			entityManager.clear();
			return false;
		}
	}
}
