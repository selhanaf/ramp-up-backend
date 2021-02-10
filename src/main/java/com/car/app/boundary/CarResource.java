package com.car.app.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.controller.ICarService;
import com.car.app.model.Car;


@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource implements ICarResource {
	private static Logger log = LoggerFactory.getLogger(CarResource.class);
	
	@Inject
	private ICarService carService;
	
	@GET
    public Response getCars() {
		log.info("ENETR : getCars");
        List<Car> cars = carService.getCars();
        log.info("EXIT: getCars");
		return Response.status(Status.OK).entity(cars).build();
    }
	
	@GET
	@Path("/{carId}")
	public Response getCar(@PathParam("carId") String carId) {
		log.info("ENETR : getCar");
		Car car = carService.getCar(carId);
		log.info("EXIT: getCar");
		return Response.status(Status.OK).entity(car).build();
	}
	
	@POST
	public Response createCar(Car car) throws Exception {
		log.info("ENETR : createCar");
		car = carService.createCar(car);
		log.info("EXIT : createCar");
		return Response.status(Status.CREATED).entity(car).build();
	}
	
	@PUT
	public Response updateCar(Car car) throws Exception {
		log.info("ENETR :");
		car = carService.updateCar(car);
		log.info("EXIT: ");
		return Response.status(Status.OK).entity(car).build();
	}
	
	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") String carId) {
		log.info("ENTER : deleteCar");
		boolean deleted = carService.deleteCar(carId);
		Status status = deleted? Status.NO_CONTENT : Status.NOT_FOUND;
		log.info("EXIT: deleteCar");
		return Response.status(status).build();
	}
	
}
