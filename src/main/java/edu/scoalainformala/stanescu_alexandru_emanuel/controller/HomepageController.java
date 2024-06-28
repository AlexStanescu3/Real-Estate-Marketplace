package edu.scoalainformala.stanescu_alexandru_emanuel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomepageController {

    @GetMapping("/home")
    public String getHomePage(Model model) {
        return "index.html";
    }
}
