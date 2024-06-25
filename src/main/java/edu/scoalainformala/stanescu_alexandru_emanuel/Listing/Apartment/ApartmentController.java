package edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Apartment;

import jakarta.annotation.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/apartment")

public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping("/all")
    public String getApartments(Model model) {

        model.addAttribute("apartments", apartmentService.getAll());

        return "apartments.html";

    }

    @GetMapping("/{id}")
    public String getApartment(@PathVariable int id, Model model) {

        model.addAttribute("apartment", apartmentService.get(id));

        return "updateListing";

    }

    @GetMapping("/view/{id}")
    public String viewApartment(@PathVariable int id, Model model) {

        model.addAttribute("apartment", apartmentService.get(id));

        return "viewApartment";

    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("apartment", new Apartment());

        return "createApartment";
    }

    @PostMapping("/create")
    public String createApartment(@ModelAttribute Apartment apartment, @RequestParam("images") List<MultipartFile> images, Model model) {
        try {
            System.out.println("am intrat in controller");
            apartmentService.save(apartment, images);
        } catch (Exception e) {
            System.out.println("am intrat mai adanc in controller");
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error saving apartment. Please try again.");
            return "createApartment";
        }
        return "redirect:/apartment/all";
    }


//    @PostMapping
//    public String createApartment(@ModelAttribute Apartment apartment) {
//
//        apartmentService.add(apartment);
//        return "redirect:/apartment/all";
//    }

//    @PostMapping
//    public String createApartmentWithImages(@ModelAttribute Apartment apartment, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "error " + bindingResult.getAllErrors();
//        }
//        try {
//            apartmentService.save(apartment);
//            return "redirect:/apartment/all";
//        } catch (IOException e) {
//            return e.getMessage();
//        }
//    }

//    @PostMapping
//    public String createApartmentWithImages(@ModelAttribute Apartment apartment, @RequestParam("images") List<MultipartFile> images) {
//        try {
//            apartmentService.save(apartment, images);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "error";
//        }
//        return "redirect:/apartment/all";
//    }

    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute Apartment apartment) {

        apartmentService.update(id, apartment);

        return "redirect:/apartment/all";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        apartmentService.delete(id);

        return "redirect:/apartment/all";
    }


}
