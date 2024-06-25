package edu.scoalainformala.stanescu_alexandru_emanuel.controller;

import edu.scoalainformala.stanescu_alexandru_emanuel.domain.User;
import edu.scoalainformala.stanescu_alexandru_emanuel.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public String getUsers(Model model) {

        model.addAttribute("users", userService.getAll());

        return "users.html";

    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable int id, Model model) {

        model.addAttribute("user", userService.get(id));

        return "updateUser";

    }

    @GetMapping("/create")
    public String createUser(Model model)
    {
            model.addAttribute("user", new User());

        return "createUser";
    }


    @PostMapping
    public String create(@ModelAttribute User user)
    {

        userService.add(user);

        return "redirect:/user/all";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute User user)
    {

        userService.update(id, user);

        return "redirect:/user/all";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        userService.delete(id);

        return "redirect:/user/all";
    }

}
