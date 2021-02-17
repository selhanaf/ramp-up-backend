package com.car.app.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.car.app.model.Car;
import com.car.app.model.Country;
import com.car.app.model.Brand;
import com.car.app.model.dto.CarDto;
import com.car.app.utilities.PaginationObject;

@RunWith(MockitoJUnitRunner.class)
public class CarDaoTest {

	@InjectMocks
	CarDao carDao = new CarDao();

	@Mock
	EntityManager entityManager;

	@Mock
	Query query;

	@Mock
	TypedQuery<Car> createQuery;

	@Mock
	CriteriaQuery<Long> countQuery;

	@Captor
	ArgumentCaptor<String> argument;

	@Mock
	CriteriaBuilder cb;
	
	@Mock
	CriteriaQuery<Car> q;
	
	@Mock
	Root<Car> root;
	
	@Mock
	TypedQuery<Long> typedCount;

	List<Car> cars;
	
	List<Brand> brands;
	
	List<Country> countries;

	/**
	 * Configuration before any test method
	 */
	@Before
	public void init() {
		
		cars = new ArrayList<Car>();
		for (int i = 0; i < 5; i++) {
			Country country = new Country();
			country.setName("country "+i);
			Brand brand = new Brand();
			brand.setName("brand "+i);
			Car car = new Car();
			car.setBrand(brand);
			car.setCountry(country);
			car.setRegistration(new Date());
			car.setId("id-" + i);
			cars.add(car);
		}

	}

	/**
	 * Testing the method findAllCars
	 * 
	 */
	@Test
	public void testFindAllCars() {
		// mock the query:
		when(entityManager.getCriteriaBuilder()).thenReturn(cb);
		when(cb.createQuery(Car.class)).thenReturn(q);
		when(cb.createQuery(Long.class)).thenReturn(countQuery);
		when(q.from(Car.class)).thenReturn(root);
		when(entityManager.createQuery(countQuery)).thenReturn(typedCount);
		when(entityManager.createQuery(q)).thenReturn(createQuery);
		when(typedCount.getSingleResult()).thenReturn(10l);
		when(createQuery.getResultList()).thenReturn(cars);

		PaginationObject<CarDto> cars2 = carDao.findAllCars(0, 0, null, null, null);

		// check if the cars are well gotten
		assertEquals(cars2.data.size(), cars.size());
	}

	/**
	 * Testing the method of findCarById
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
	 * 
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
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	public void testUpdateCar() throws Exception {
		when(entityManager.find(any(), anyString())).thenReturn(cars.get(0));
		when(entityManager.merge(any(Car.class))).thenReturn(cars.get(0));

		Car updateCar = carDao.updateCar(cars.get(0));

		assertEquals(cars.get(0), updateCar);

	}
}
