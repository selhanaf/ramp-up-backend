package com.car.app.jms;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.model.Car;
import com.car.app.utilities.LogInterceptor;

@Stateless
@Interceptors(LogInterceptor.class)
public class JmsSender {
	
	private static Logger log = LoggerFactory.getLogger(JmsSender.class);
	
	@Resource(mappedName = "jms/queue")
	private Queue dest;
	
	@Resource(mappedName = "jms/connectionFactory")
	private ConnectionFactory queue;
	
	public void updateCarJms(Car car) {
		try {
			Connection connection = queue.createConnection();
			Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
			MessageProducer mp = session.createProducer(dest);
			mp.send(session.createObjectMessage(car));
		} catch (JMSException e) {
			log.error(e.getMessage());
		}
	}
}
