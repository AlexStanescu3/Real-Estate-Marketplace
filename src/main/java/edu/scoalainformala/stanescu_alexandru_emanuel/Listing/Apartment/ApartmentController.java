package edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Apartment;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

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
        Apartment apartment = apartmentService.get(id);
        List<byte[]> imageBytesList = apartment.getImageData(); // Assuming Apartment has List<byte[]> imageData

        List<String> base64Images = imageBytesList.stream()
                .map(imageData -> Base64.getEncoder().encodeToString(imageData))
                .collect(Collectors.toList());

        model.addAttribute("apartment", apartment);
        model.addAttribute("images", base64Images);
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
            System.out.println(" the multipart file "+images);
            apartmentService.save(apartment, images);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error saving apartment. Please try again.");
            return "createApartment";
        }
        return "redirect:/apartment/all";
    }

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
