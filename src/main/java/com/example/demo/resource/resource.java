package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class resource {

	@Value("${application.activemq.tcp}")
	private String tcp;

	@Value("${application.activemq.userName}")
	private String userName;

	@Value("${application.activemq.passWord}")
	private String Password;

	@Value("${application.activemq.IncomeMsg}")
	private String Income;

	@Value("${application.activemq.queueNameResponse}")
	private String responseQueue;
	
	@Value("${application.activemq.queueAcuses}")
	private String acuseQueue;
	

	
	public String getResponseQueue() {
		return responseQueue;
	}

	public String getAcuseQueue() {
		return acuseQueue;
	}

	public void setAcuseQueue(String acuseQueue) {
		this.acuseQueue = acuseQueue;
	}

	public String getTcp() {
		return tcp;
	}

	public String getPassword() {
		return Password;
	}

	public String getIncome() {
		return Income;
	}


	public String getUserName() {
		return userName;
	}

	@Override
	public String toString() {
		return "resourcesSpeiProcess [tcp=" + tcp + ", userName=" + userName + ", Password=" + Password + ", Income="
				+ Income + ", responseQueue=" + responseQueue + ", acuseQueue=" + acuseQueue + "]";
	}
	
	
}
