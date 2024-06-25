package edu.scoalainformala.stanescu_alexandru_emanuel.Listing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/listing")

public class ListingController {

    @GetMapping("/create")
    public String createListing(Model model) {

        return "createListing.html";

    }

    @GetMapping("/all")
    public String getListing(Model model) {

        return "allListings.html";

    }
}
