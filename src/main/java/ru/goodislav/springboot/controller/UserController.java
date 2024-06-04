package ru.goodislav.springboot.controller;

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
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "redirect:/users";
    }
    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }
    @GetMapping
    public ModelAndView getAllUsers() {
        List<User> users = userService.getAllUsers();
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
        User user = userService.getUserById(id);
        if (user != null) {
            user.setName(name);
            user.setLastname(lastname);
            user.setAge(age);
            user.setEmail(email);
            user.setPassword(password);
            userService.updateUser(user);
        }
        return "redirect:/users";
    }
    @GetMapping("/edit")
    public ModelAndView editUser(@RequestParam Long id) {
        User user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView("editUser");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}