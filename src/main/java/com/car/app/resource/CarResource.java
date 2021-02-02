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
public class CarResource {
	private static Logger log = LoggerFactory.getLogger(CarResource.class);
	
	private CarService carService = new CarService();
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCars() {
        List<Car> cars = carService.getCars();
		return Response.status(Status.OK).entity(cars).build();
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{carId}")
	public Response getCar(@PathParam("carId") String carId) {
		Car car = carService.getCar(carId);
		return Response.status(Status.OK).entity(car).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCar(Car car) throws Exception {
		log.info("CREATE NEW CAR RESOURCE");
		car = carService.createCar(car);
		return Response.status(Status.CREATED).entity(car).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateCar(Car car) throws Exception {
		log.info("CREATE NEW CAR RESOURCE");
		car = carService.updateCar(car);
		return Response.status(Status.OK).entity(car).build();
	}
	
	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") String carId) {
		boolean deleted = carService.deleteCar(carId);
		Status status = deleted? Status.NO_CONTENT : Status.NOT_FOUND;
		return Response.status(status).build();
	}
	
}
