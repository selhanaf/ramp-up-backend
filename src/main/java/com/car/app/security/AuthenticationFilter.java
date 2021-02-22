package com.car.app.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.client.auth.AuthAPI;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthenticationFilter  implements ContainerRequestFilter {
	final static Logger log = LoggerFactory.getLogger(AuthenticationFilter.class);
	
	
	AuthAPI auth = new AuthAPI("dev-04zom-rc.us.auth0.com", "ZBg0YCTgMaEBnCcunLo5zph8l78y0htE",
			"MsWuLgKLiSZDoRN2bGBAC4rxIoPV3AFrA31HnFcpXIBN1B4uFlDhsrrfnzHvjsvs");

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		log.info("AuthenticationFilter");
		// Get the HTTP Authorization header from the request
        String token = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if(token == null) {
        	throw new NotAuthorizedException("No authorization header");
        }
        
        token = token.replace("Bearer ", "");
        
//        try {
//        	auth.userInfo(token).execute();
//		} catch (Exception e) {
//			throw new NotAuthorizedException("Token invalid");
//		}
	}

}
