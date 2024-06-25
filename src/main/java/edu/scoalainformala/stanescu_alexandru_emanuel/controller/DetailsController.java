package edu.scoalainformala.stanescu_alexandru_emanuel.controller;

import edu.scoalainformala.stanescu_alexandru_emanuel.domain.User;
import edu.scoalainformala.stanescu_alexandru_emanuel.service.DetailsService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/details")
@RequiredArgsConstructor
public class DetailsController {

    private final DetailsService detailsService;
    private User user;



    @GetMapping
    public String getDetails(Model model) {
        System.out.println("Hello from details controller GET method");

        model.addAttribute("detailsText", detailsService.getDetails());

        return "details.html";
    }

    @PostMapping
    @ResponseBody
    public void postMethod() {
        System.out.println("Hello from details controller POST method");

    }

}
