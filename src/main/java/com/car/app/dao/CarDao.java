package com.car.app.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.model.Car;

public class CarDao {
	
	private static Logger log = LoggerFactory.getLogger(CarDao.class);
	private static final String PERSISTENCE_UNIT_NAME = "auto-car-unit";
	
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	
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
