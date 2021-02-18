package com.car.app.controller;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

import com.car.app.model.Car;

@Startup
@Singleton
public class RemoveCarSchedule {

	@PersistenceContext(unitName = "auto-car-unit")
	private EntityManager em;

	@Schedule(second = "0", minute = "0", hour = "0")
	public void scheduleDeleting() {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaDelete<Car> createCriteriaDelete = criteriaBuilder.createCriteriaDelete(Car.class);
		Root<Car> root = createCriteriaDelete.from(Car.class);
		createCriteriaDelete.where(criteriaBuilder.equal(root.get("toDelete"), true));
		em.createQuery(createCriteriaDelete).executeUpdate();
	}
}
