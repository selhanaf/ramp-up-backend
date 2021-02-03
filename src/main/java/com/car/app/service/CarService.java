package com.car.app.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.model.Car;

public class CarService implements ICarService {
	private static Logger log = LoggerFactory.getLogger(CarService.class);
	private static final String PERSISTENCE_UNIT_NAME = "auto-car-unit";
	
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	public List<Car> getCars() {
		log.info("Get all cars");
		entityManager.getTransaction().begin();
		List<Car> resultList = entityManager.createQuery("select c from Car c").getResultList();
		entityManager.getTransaction().commit();
		entityManager.clear();
		log.info("car length = {}", resultList.size());
		return resultList;
	}

	public Car getCar(String id) {
		log.info("find car with id = {}", id);
		entityManager.getTransaction().begin();
		Car car = entityManager.find(Car.class, id);
		entityManager.getTransaction().commit();
		entityManager.clear();
		return car;
	}

	public Car createCar(Car car) throws Exception {
		try {
			entityManager.getTransaction().begin();
			log.info("create new car");
			entityManager.persist(car);
			entityManager.getTransaction().commit();
			entityManager.clear();
			return car;
		} catch (Exception e) {
			log.error("error occured", e);
			throw new Exception("Error while creating a new car");
		}
		
	}

	public Car updateCar(Car car) throws Exception {
		try {
			log.info("Update the car with the id = {}", car.getId());
			entityManager.getTransaction().begin();
			Car updatedCar = entityManager.merge(car);
			entityManager.getTransaction().commit();
			entityManager.clear();
			return updatedCar;
			
		} catch (Exception e) {
			log.error("error occured", e);
			throw new Exception("Error while updating a car");
		}
		
	}

	public boolean deleteCar(String carId) {
		log.info("remove car with id ={} ", carId);
		entityManager.getTransaction().begin();
		Car car = entityManager.find(Car.class, carId);
		if(car != null) {
			entityManager.remove(car);
			entityManager.getTransaction().commit();
			entityManager.clear();
			return true;
		} else {
			entityManager.getTransaction().commit();
			entityManager.clear();
			return false;
		}
	}
	
}
