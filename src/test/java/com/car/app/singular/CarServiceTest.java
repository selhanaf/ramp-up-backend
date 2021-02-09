package com.car.app.singular;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.car.app.model.Car;
import com.car.app.singular.CarDao;
import com.car.app.singular.CarService;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
	
	@InjectMocks
	CarService carService;
	
	
	List<Car> cars;
	
	@Mock
	private CarDao carDao;
	
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
	}
	
	
	@Test
	public void testGetAllCars() {
		Mockito.when(carDao.findAllCars()).thenReturn(cars);
		
		List<Car> cars2 = carService.getCars();
		assertEquals(cars2.size(), cars.size());
	}
	
	@Test
	public void testGetCar() {
		Mockito.when(carDao.findCarById(Mockito.anyString())).thenReturn(cars.get(0));
		Car car = carService.getCar("id-0");
		assertEquals(car.getId(), "id-0");
	}
	
	@Test
	public void testAddCar() throws Exception {
		Car car = new Car();
		car.setBrand("new car");
		car.setCountry("new Car");
		car.setRegistration(new Date());
		Mockito.when(carDao.createCar(Mockito.any(Car.class))).thenReturn(car);
		Car newCar = carService.createCar(car);
		assertEquals(newCar, car);
	}
	
	@Test
	public void testUpdateCar() throws Exception {
		Car car = new Car();
		car.setBrand("update car");
		car.setCountry("update");
		car.setRegistration(new Date());
		Mockito.when(carDao.createCar(Mockito.any(Car.class))).thenReturn(car);
		Car updateCar = carService.createCar(cars.get(0));
		assertEquals(updateCar, car);
	}
	

	@Test
	public void testDeleteCar() {
		Mockito.when(carDao.deleteCar(Mockito.anyString())).thenReturn(true);
		assertTrue(carService.deleteCar("ID"));
	}

}
