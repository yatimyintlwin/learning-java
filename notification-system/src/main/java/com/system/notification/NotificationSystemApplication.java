package com.system.notification;

import com.system.notification.configuration.AppConfig;
import com.system.notification.exception.UserNotFoundException;
import com.system.notification.model.User;
import com.system.notification.service.NotificationService;
import com.system.notification.service.SMSService;
import com.system.notification.service.UserService;
import com.system.notification.service.impl.NotificationServiceImpl;
import com.system.notification.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class NotificationSystemApplication {
	public static void main(String[] args) throws UserNotFoundException {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				AppConfig.class, UserServiceImpl.class, NotificationServiceImpl.class
		);

		NotificationService notificationService = context.getBean(NotificationService.class);
		UserService userService = context.getBean(UserService.class);

		SMSService smsService = context.getBean("SMSService", SMSService.class);
		smsService.sendMessage("sms send message");

		notificationService.sendNotification("This is a test message.");

		userService.addUser(new User(1, "Alice", "alice@example.com"));
		userService.addUser(new User(2, "Bob", "bob@example.com"));
		userService.addUser(new User(3, "Charlie", "charlie@example.com"));

		List<User> filterUserByDomain = userService.getUsersByDomain("example.com");
		System.out.println("Users with example.com domain: " + filterUserByDomain.size() + " users");

		userService.getUserById(99);

//		try {
//			userService.getUserById(99);
//		} catch (UserNotFoundException e) {
//			System.out.println(e.getMessage());
//		}
	}
}
