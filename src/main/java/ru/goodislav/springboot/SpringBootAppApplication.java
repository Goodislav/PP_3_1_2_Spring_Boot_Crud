package ru.goodislav.springboot;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.goodislav.springboot.model.User;
import ru.goodislav.springboot.service.UserService;

@SpringBootApplication
public class SpringBootAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppApplication.class, args);

    }
    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        userService.addUser(new User("User1", "Lastname1", 20, "a@a.com", "1111"));
        userService.addUser(new User("User2", "Lastname2", 21, "b@b.com", "2222"));
        userService.addUser(new User("User3", "Lastname3", 22, "c@c.com", "3333"));
    }

}
