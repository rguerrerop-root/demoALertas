package com.example.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import com.example.demo.resource.resource;

@SpringBootApplication
public class DemoApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		resource resources = context.getBean(resource.class);

		DateFormat dateFormatshort = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();

		try {

			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(resources.getTcp());
			connectionFactory.setUserName(resources.getUserName());
			connectionFactory.setPassword(resources.getPassword());
			Connection connection = connectionFactory.createConnection();
			connection.start();

			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(resources.getIncome());

			MessageConsumer consumer = session.createConsumer(destination);

			consumer.setMessageListener(new MessageListener() {

				@Override
				public void onMessage(Message message) {
					TextMessage textMessage = (TextMessage) message;

					String decodedString = null;
					String Endcrypttext = null;

					try {
						LOGGER.info(" mensaje recuperado: {} ", textMessage.getText());
						LOGGER.info(textMessage.getJMSCorrelationIDAsBytes().toString());

					} catch (Exception e) {
						LOGGER.error(e.getMessage());
					}

				}

			});

		} catch (JMSException e) {
			System.out.println(e.getMessage());
		}

	}
}
