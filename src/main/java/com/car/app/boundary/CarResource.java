package com.car.app.boundary;


import javax.annotation.Resource;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
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
	@Inject
	private ICarService carService;
	
	@Resource(mappedName = "jms/queue")
	private Queue dest;
	
	@Resource(mappedName = "jms/connectionFactory")
	private ConnectionFactory queue;
	
	
	
	@GET
    public Response getCars(@QueryParam("size") int size,
    		@QueryParam("page")int page,
    		@QueryParam("sort") @DefaultValue("asc") String sort,
    		@QueryParam("order") @DefaultValue("brand") String order,
    		@QueryParam("search") String search) {
		PaginationObject<CarDto> result = carService.getCars(size, page, sort, order, search);
		return Response.status(Status.OK).entity(result).build();
    }
	
	@GET
	@Path("/{carId}")
	public Response getCar(@PathParam("carId") String carId) {
		CarDto car = carService.getCar(carId);
		Status status = car != null ? Status.OK : Status.NOT_FOUND;
		
		return Response.status(status).entity(car != null ? car : "Not Found").build();
	}
	
	@POST
	public Response createCar(Car car) throws Exception {
		CarDto carDto = carService.createCar(car);
		return Response.status(Status.CREATED).entity(carDto).build();
	}
	
	@PUT
	public Response updateCar(Car car) throws Exception {
		try {
//			CarDto carDto = carService.updateCar(car);
			Connection connection = queue.createConnection();
			Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			MessageProducer mp = session.createProducer(dest);
			mp.send(session.createObjectMessage(car));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(true).build();
	}
	
	@DELETE
	@Path("/{carId}")
	public Response deleteCar(@PathParam("carId") String carId) {
		boolean deleted = carService.deleteCar(carId);
		Status status = deleted? Status.NO_CONTENT : Status.NOT_FOUND;
		return Response.status(status).build();
	}
	
}
