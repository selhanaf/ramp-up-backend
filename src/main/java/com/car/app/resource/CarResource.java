package com.car.app.resource;

import java.util.List;

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

import com.car.app.model.Car;
import com.car.app.service.CarService;

@Path("cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CarResource {
	private static Logger log = LoggerFactory.getLogger(CarResource.class);
	
	private CarService carService = new CarService();
	
	@GET
    public Response getCars() {
		log.info("GET ALL CARS RESOURCE");
        List<Car> cars = carService.getCars();
        log.info("CARS WERE GOTTEN SUCCESSFULLY FROM SERVICE");
		return Response.status(Status.OK).entity(cars).build();
    }
	
	@GET
	@Path("/{carId}")
	public Response getCar(@PathParam("carId") String carId) {
		log.info("GET CAR");
		Car car = carService.getCar(carId);
		log.info("CARS WERE GOTTEN SUCCESSFULLY FROM SERVICE");
		return Response.status(Status.OK).entity(car).build();
	}
	
	@POST
	public Response createCar(Car car) throws Exception {
		log.info("CREATE NEW CAR RESOURCE");
		car = carService.createCar(car);
		return Response.status(Status.CREATED).entity(car).build();
	}
	
	@PUT
	public Response updateCar(Car car) throws Exception {
		log.info("UPDATE A CAR - RESOURCE");
		car = carService.updateCar(car);
		return Response.status(Status.OK).entity(car).build();
	}
	
	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") String carId) {
		log.info("REMOVE A CAR - RESOURCE");
		boolean deleted = carService.deleteCar(carId);
		Status status = deleted? Status.NO_CONTENT : Status.NOT_FOUND;
		return Response.status(status).build();
	}
	
}
