package com.example.demo;

import com.example.demo.service.BusinessLogicService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoProducerApplication {

	public static void main(String[] args) {

        var context = SpringApplication.run(DemoProducerApplication.class, args);
        var service = context.getBean(BusinessLogicService.class);
        service.createAndSendMessage();
	}
}
