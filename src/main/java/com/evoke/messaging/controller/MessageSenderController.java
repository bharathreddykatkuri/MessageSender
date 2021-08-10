package com.evoke.messaging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.evoke.messaging.model.Employee;
import com.evoke.messaging.service.RabbitMQSender;

@RestController
@RequestMapping(value = "/send/")
public class MessageSenderController {

	@Autowired
	RabbitMQSender rabbitMQSender;

	@GetMapping(value = "/producer")
	public String producer(@RequestParam("empName") String empName,@RequestParam("empId") String empId) {
		Integer employeeID = Integer.parseInt(empId);
		Employee emp=new Employee();
		emp.setEmployeeID(employeeID);
		emp.setName(empName);
		rabbitMQSender.send(emp);
	return "Message sent to the RabbitMQ Successfully";
	}

}

