package com.car.app.utilities;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * @author selhanaf
 * 
 * A class used as annotation to make Entering/Leaving methods
 *
 */
public class LogInterceptor {
	private static Logger log = LoggerFactory.getLogger(LogInterceptor.class);

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception {
		log.info("ENETR: " + context.getMethod().getName());
 
		Object result = context.proceed();
 
		log.info("EXIT: " + context.getMethod().getName()  );
 
		return result;
	}
}
