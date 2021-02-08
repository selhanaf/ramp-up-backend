package com.car.app.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.model.Car;

/**
 * @author selhanaf
 * 
 * CarDao is a class used to make interaction with database
 *
 */
public class CarDao {
	
	private static Logger log = LoggerFactory.getLogger(CarDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "auto-car-unit";
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	/**
	 * getting all cars
	 * @return
	 */
	public List<Car> findAllCars() {
		log.info("ENTER : findAllCars");
		entityManager.getTransaction().begin();
		List<Car> resultList = entityManager.createQuery("select c from Car c").getResultList();
		entityManager.getTransaction().commit();
		entityManager.clear();
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
		entityManager.getTransaction().begin();
		Car car = entityManager.find(Car.class, id);
		entityManager.getTransaction().commit();
		entityManager.clear();
		log.info("EXIT : findCarById");
		return car;
	}

	/**
	 * create a new car
	 * @param car
	 * @return
	 * @throws Exception 
	 */
	public Car createCar(Car car) throws Exception {
		log.info("ENTER : createCar");
		try {
			entityManager.getTransaction().begin();
			log.info("create new car");
			entityManager.persist(car);
			entityManager.getTransaction().commit();
			entityManager.clear();
			log.info("EXIT : createCar");
			return car;
		} catch (Exception e) {
			log.error("error occured", e);
			throw new Exception("Error while creating a new car");
		}
		
	}

	/**
	 * update existing car
	 * @param car
	 * @return
	 * @throws Exception
	 */
	public Car updateCar(Car car) throws Exception {
		log.info("ENTER : updateCar");
		try {
			log.info("Update the car with the id = {}", car.getId());
			entityManager.getTransaction().begin();
			Car updatedCar = entityManager.merge(car);
			entityManager.getTransaction().commit();
			entityManager.clear();
			log.info("EXIT : updateCar");
			return updatedCar;
			
		} catch (Exception e) {
			log.error("error occured", e);
			throw new Exception("Error while updating a car");
		}
		
	}

	/**
	 * delete a car with it's ID
	 * @param carId
	 * @return
	 */
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
