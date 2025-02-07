package com.mykyda;

import com.mykyda.security.database.entity.Role;
import com.mykyda.security.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CheeseQuestApplication {
    public static void main(String[] args) {
        var context = SpringApplication.run(CheeseQuestApplication.class, args);
//        var userService = context.getBean(UserService.class);
//        System.out.println("Created: " +
//                userService.reg("8x2@gmail.com", "22222222", Role.USER));
    }
}
