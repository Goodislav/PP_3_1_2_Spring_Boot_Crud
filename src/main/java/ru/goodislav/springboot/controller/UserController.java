package ru.goodislav.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.goodislav.springboot.model.User;
import ru.goodislav.springboot.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        logger.info("Adding user: {}", user);
        userService.addUser(user);
        logger.info("User added successfully: {}", user);
        return "redirect:/users";
    }
    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        logger.info("Showing add user form");
        model.addAttribute("user", new User());
        return "addUser";
    }
    @GetMapping
    public ModelAndView getAllUsers() {
        logger.info("Getting all users");
        List<User> users = userService.getAllUsers();
        logger.info("Number of users got: {}", users.size());
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", users);
        return modelAndView;
    }
    @PostMapping("/updateUser")
    public String updateUser(@RequestParam("id") Long id,
                             @RequestParam("name") String name,
                             @RequestParam("lastname") String lastname,
                             @RequestParam("age") int age,
                             @RequestParam("email") String email,
                             @RequestParam("password") String password) {
        logger.info("Updating user with id: {}", id);
        User user = userService.getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setLastname(lastname);
            user.setAge(age);
            user.setEmail(email);
            user.setPassword(password);
            userService.updateUser(user);
            logger.info("User updated successfully: {}", user);
        } else {
            logger.info("User with id: {} not found", id);
        }
        return "redirect:/users";
    }
    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam Long id) {
        logger.info("Editing user with id: {}", id);
        User user = userService.getUserById(id);
        if (user != null) {
            logger.info("User found: {}", user);
        } else {
            logger.info("User with id: {} not found", id);
        }
        ModelAndView modelAndView = new ModelAndView("editUser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        logger.info("Deleting user with id: {}", id);
        userService.deleteUser(id);
        logger.info("User deleted successfully: {}", id);
        return "redirect:/users";
    }
}