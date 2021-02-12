package com.car.app.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

/**
 * @author selhanaf
 * we'll use this interface to bind our to authorization filter
 */
@NameBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface SecuredApi {

}
