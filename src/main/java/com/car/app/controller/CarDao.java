package com.car.app.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
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
 *         CarDao is a class used to make interaction with database
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
	 * 
	 * @return List of cars list of cars
	 */
	public PaginationObject<CarDto> findAllCars(int size, int page, String sort, String order, String search) {
		sort = sort != null && !sort.trim().isEmpty() && sort.equalsIgnoreCase("desc") ? sort : "asc";
		order = order != null && !order.trim().isEmpty() ? order : "id";
		size = size <= 0 || size >= 50 ? 10 : size;
		page = page <= 0 ? 0 : page;
		
		// testing with criteria
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		// create criteria queries
		CriteriaQuery<Car> q = cb.createQuery(Car.class);
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		
		// creating routing 
		Root<Car> root = q.from(Car.class);
        
        countQuery.select(cb.count(countQuery.from(Car.class)));
        
		if (search != null) {
			Predicate brandPredict = cb.like(root.get("brand"), "%" + search + "%");
			Predicate countryPredict = cb.like(root.get("country"), "%" + search + "%");
			Predicate mergePredicates = cb.or(brandPredict, countryPredict);
			q.where(mergePredicates).distinct(true);
			countQuery.where(mergePredicates).distinct(true);
		}

		// take the order
		q.orderBy(sort == "asc" ? cb.asc(root.get(order)) : cb.desc(root.get(order)));

		TypedQuery<Car> createQuery = entityManager.createQuery(q);

		// count total elements
        TypedQuery<Long> createQuery2 = entityManager.createQuery(countQuery);
		Long singleResult = createQuery2.getSingleResult();

		createQuery.setMaxResults(size);
		createQuery.setFirstResult(page * size);

		List<Car> resultList = createQuery.getResultList();
		List<CarDto> data = resultList.stream().map(c -> CarDto.convertCarToDto(c)).collect(Collectors.toList());
		PaginationObject<CarDto> pagintationObject = new PaginationObject<CarDto>(singleResult.intValue(), size, page,
				data, sort, order);
		return pagintationObject;
	}

	/**
	 * find car by ID
	 * 
	 * @param id id of the car to get
	 * @return Car
	 */
	public Car findCarById(String id) {
		log.info("find car with id = {}", id);
		Car car = entityManager.find(Car.class, id);
		return car;
	}

	/**
	 * create a new car
	 * 
	 * @param car car to create
	 * @return Car return the created Car
	 */
	@Transactional(REQUIRED)
	public Car createCar(Car car) {
		entityManager.persist(car);
		return car;

	}

	/**
	 * update existing car
	 * 
	 * @param car Car to update
	 * @return Car updated car
	 * @throws Exception throws exception
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
	 * 
	 * @param carId the id of car to delete
	 * @return boolean true if deleted false if not
	 */
	@Transactional(REQUIRED)
	public boolean deleteCar(String carId) {
		log.info("remove car with id ={} ", carId);
		Car car = entityManager.find(Car.class, carId);
		if (car != null) {
			entityManager.remove(car);
			return true;
		} else {
			return false;
		}
	}

}
