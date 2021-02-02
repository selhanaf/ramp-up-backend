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

	public Car createCar(Car car) {
		entityManager.getTransaction().begin();
		if(car.getId() == null) {
			log.info("create new car");
			entityManager.persist(car);
		} else {
			car = entityManager.merge(car);
		}
		entityManager.getTransaction().commit();
		entityManager.clear();
		return car;
	}

	public Car updateCar(Car car) {
		entityManager.getTransaction().begin();
		car = entityManager.merge(car);
		entityManager.getTransaction().commit();
		entityManager.clear();
		return car;
	}

	public boolean deleteCar(String carId) {
		log.info("remove car with id ={} ", carId);
		Car car = entityManager.find(Car.class, carId);
		if(car != null) {
			entityManager.remove(car);
			entityManager.getTransaction().commit();
			entityManager.clear();
			return false;
		} else {
			entityManager.getTransaction().commit();
			entityManager.clear();
			return false;
		}
	}
	
	public static void main(String[] args) {
		CarService service = new CarService();
		Car car = new Car();
		car.setBrand("updated");
		car.setCountry("updated");
		car.setId("e8eac5a1-3870-4158-9281-007354be3332");
		car.setRegistration(new Date());
		Car cars = service.updateCar(car);
		log.info("cars length = {}", cars.getBrand());
	}

}
