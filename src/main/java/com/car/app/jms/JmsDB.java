package com.car.app.jms;

import java.io.Serializable;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.car.app.controller.ICarService;
import com.car.app.model.Car;

/**
 * Message-Driven Bean implementation class for: JmsDB
 */
@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue"),
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "MyQUEUE"),
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.12.0")
		}, 
		mappedName = "dest")
public class JmsDB implements MessageListener {
	
	private static Logger log = LoggerFactory.getLogger(JmsDB.class);

	@Inject
	private ICarService carService;
	
	
    public void onMessage(Message message) {
    	
    	ObjectMessage obj = (ObjectMessage)message;
    	try {
    		Car car = (Car) obj.getObject();
			log.info("PRINT CARID = "+car.getId());
			carService.updateCar(car);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
