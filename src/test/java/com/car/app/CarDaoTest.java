package com.car.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.car.app.controllers.CarDao;
import com.car.app.model.Car;

@RunWith(MockitoJUnitRunner.class)
public class CarDaoTest {

	@InjectMocks
	CarDao carDao;
	
	@Mock
	EntityManager entityManager;
	
	@Mock
	EntityTransaction transaction;
	
	@Mock
	Query query;
	
	List<Car> cars;
	
	/**
	 * Configuration before any test method
	 */
	@Before
	public void init() {
		
		cars = new ArrayList<Car>();
		for(int i = 0; i < 5; i++) {
			Car car = new Car();
			car.setBrand("Brand "+i);
			car.setCountry("Country - "+i);
			car.setRegistration(new Date());
			car.setId("id-"+i);
			cars.add(car);
		}
		
		// mock the entity manager
		when(entityManager.getTransaction()).thenReturn(transaction);
		doNothing().when(transaction).begin();
		doNothing().when(transaction).commit();
		doNothing().when(entityManager).clear();
	}
	
	/**
	 *  Testing the method findAllCars
	 */
	@Test
	public void testFindAllCars() {
		
		// mock the query:
		when(entityManager.createQuery(anyString())).thenReturn(query);
		when(query.getResultList()).thenReturn(cars);
		
		List<Car> cars2 = carDao.findAllCars();
		assertEquals(cars2.size(), cars.size());
	}
	
	/**
	 *  Testing the method of findCarById
	 */
	@Test
	public void testFindCarById() {
		when(entityManager.find(any(), anyString())).thenReturn(cars.get(0));
		Car findCar = carDao.findCarById("any");
		assertEquals(cars.get(0), findCar);
	}
	
	/**
	 * 
	 * Testing createCar
	 * @throws Exception 
	 *  
	 */
	@Test
	public void testCreateCar() throws Exception {
		doNothing().when(entityManager).persist(any(Car.class));
		
		Car createCar = carDao.createCar(cars.get(0));
		
		assertEquals(cars.get(0), createCar);
		
	}
	

	/**
	 * 
	 * Testing updateCar
	 * @throws Exception 
	 *  
	 */
	@Test
	public void testUpdateCar() throws Exception {
		when(entityManager.merge(any(Car.class))).thenReturn(cars.get(0));
		
		Car updateCar = carDao.updateCar(cars.get(0));
		
		assertEquals(cars.get(0), updateCar);
		
	}
}
