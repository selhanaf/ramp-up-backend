package com.car.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.model.Car;
import com.car.app.model.dto.CarDto;
import com.car.app.utilities.LogInterceptor;
import com.car.app.utilities.PaginationObject;

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
	public PaginationObject<CarDto> findAllCars(int size, int page, String sort, String order, String search, String searchBy) {
		sort = sort != null && !sort.trim().isEmpty() ? sort : "asc";
		order = order != null && !order.trim().isEmpty() ? order : "id";
		size = size == 0 ? 10 : size;
		String query = filteringAndSortingQuery(sort, order, search, searchBy);
		Query dr = entityManager.createQuery(query);
		
		// get total elements
		int totalElmenets = dr.getResultList().size();
		dr.setFirstResult(page * size);
		dr.setMaxResults(size);
		List<Car> resultList = dr.getResultList();
//		resultList.stream().map(car -> CarDto.convertCarToDto(car)).collect(Collectors.toList());
		List<CarDto> data = resultList.stream().map(c-> CarDto.convertCarToDto(c)).collect(Collectors.toList());
		PaginationObject<CarDto> pagintationObject = new PaginationObject<CarDto>(totalElmenets, size, page, data, sort, order);
		log.info("car length = {}", resultList.size());
		return pagintationObject;
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
	
	static public String filteringAndSortingQuery(String sort, String order, String search, String searchBy) {
		
		String query = "SELECT c  FROM Car c ";
		if(search != null && searchBy != null) {
			query = query + "WHERE "+ searchBy+ " like \'%"+ search +"%\' ";
		}
		
		query+= "order by "+ order +" "+ sort;
		
		return query;
	}
}
