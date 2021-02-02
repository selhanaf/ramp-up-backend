package com.car.app;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.car.app.model.Car;

/**
 * @author selhanaf
 *
 * this is the main class used to insert/add new record to the data base
 */
public class App {

	private static Logger log = LoggerFactory.getLogger(App.class);
	private static final String PERSISTENCE_UNIT_NAME = "auto-car-unit";
	
	static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	static EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	
	/**
	 * method to create new record in database
	 * @param entityManager
	 */
	public static void createCars(EntityManager entityManager) {
		entityManager.getTransaction().begin();
		for(int i = 0; i < 9; i++){
			log.info("create the car number {}", i);
			
			Car car = new Car();
			car.setBrand("Brand" + i);
			car.setCountry("Coountr" + i);
			Date reg = new Date();
			reg.setDate(i);
			reg.setYear( new Date().getYear() - i);
			car.setRegistration(reg);
			// save the new car
			entityManager.persist(car);
		}
		entityManager.getTransaction().commit();
		entityManager.clear();
	}
	
	public static void main(String[] args) {
		
		log.info("Run main class");
		
		// create a car instance
		createCars(entityManager);
		
		
		log.info("end of main");
		
	}
	
}
