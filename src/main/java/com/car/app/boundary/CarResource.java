package com.car.app.boundary;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.controller.ICarService;
import com.car.app.model.Car;
import com.car.app.model.dto.CarDto;
import com.car.app.utilities.LogInterceptor;
import com.car.app.utilities.PaginationObject;


@Path("/cars")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Interceptors(LogInterceptor.class)
public class CarResource implements ICarResource {
	private static Logger log = LoggerFactory.getLogger(CarResource.class);
	
	@Inject
	private ICarService carService;
	
	@GET
    public Response getCars(@QueryParam("size") int size,
    		@QueryParam("page") int page,
    		@QueryParam("sort") String sort,
    		@QueryParam("order") String order,
    		@QueryParam("search") String search,
    		@QueryParam("searchBy") String searchBy) {
		PaginationObject<CarDto> result = carService.getCars(size, page, sort, order, search, searchBy);
		return Response.status(Status.OK).entity(result).build();
    }
	
	@GET
	@Path("/{carId}")
	public Response getCar(@PathParam("carId") String carId) {
		CarDto car = carService.getCar(carId);
		return Response.status(Status.OK).entity(car).build();
	}
	
	@POST
	public Response createCar(Car car) throws Exception {
		CarDto carDto = carService.createCar(car);
		return Response.status(Status.CREATED).entity(carDto).build();
	}
	
	@PUT
	public Response updateCar(Car car) throws Exception {
		CarDto carDto = carService.updateCar(car);
		return Response.status(Status.OK).entity(carDto).build();
	}
	
	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") String carId) {
		boolean deleted = carService.deleteCar(carId);
		Status status = deleted? Status.NO_CONTENT : Status.NOT_FOUND;
		return Response.status(status).build();
	}
	
}
