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
        userService.addUser(new User("Sergey", "Sergeev", 30,
                "sergey_sergeev@gmail.com","gG14%sdfhhjkh"));
        userService.addUser(new User("Petr", "Petrov", 27,
                "petr_petrov@yahoo.com", "jklSdf88^sdfss"));
        userService.addUser(new User("Maria", "Kovaleva", 25,
                "maria_kovaleva@ya.com", "fgdssd72hjT#"));
    }

}
