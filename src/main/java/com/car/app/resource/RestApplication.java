package com.car.app.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletConfig;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.integration.OpenApiConfigurationException;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;


@ApplicationPath("/api")
public class RestApplication extends Application {
	 @SuppressWarnings("rawtypes")
	public RestApplication() {
	        super();
	        OpenAPI oas = new OpenAPI();
	        oas.setServers(null);
	        Server server = new Server();
	        server.setUrl("http://localhost:8080/carapp");
	        
	        oas.setServers(Arrays.asList(server));
	        SwaggerConfiguration oasConfig = new SwaggerConfiguration()
	                .openAPI(oas)
	                .prettyPrint(true)
	                .resourcePackages(Stream.of("io.swagger.sample.resource").collect(Collectors.toSet()));

	        try {
	            new JaxrsOpenApiContextBuilder()
	                .openApiConfiguration(oasConfig)
	                .buildContext(true);
	        } catch (OpenApiConfigurationException e) {
	            throw new RuntimeException(e.getMessage(), e);
	        }

	    }
}
