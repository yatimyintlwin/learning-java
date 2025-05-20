package com.practice.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BasicSpringApplication {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		Sim sim = context.getBean("sim", Sim.class);

		sim.calling();
		sim.data();
	}
}
