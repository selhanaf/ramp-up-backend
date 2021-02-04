package com.car.app.resource;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;


@ApplicationPath("api")
public class RestApplication extends Application {
	public RestApplication() {
		BeanConfig beanConfig = new BeanConfig();
	    beanConfig.setVersion("1.0.0");
	    beanConfig.setBasePath("/api");
	    beanConfig.setHost("localhost:8080/swagger-ui");
	    beanConfig.setResourcePackage("com.car.app.resource");
	    beanConfig.setScan(true);
	}
	
	@Override
	  public Set<Class<?>> getClasses() {
	   Set<Class<?>> resources = new HashSet();
	     resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
	     resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
	     return resources;
	  }
}
