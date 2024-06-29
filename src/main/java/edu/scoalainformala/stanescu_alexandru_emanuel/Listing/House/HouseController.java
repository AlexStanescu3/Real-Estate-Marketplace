package edu.scoalainformala.stanescu_alexandru_emanuel.Listing.House;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @GetMapping("/all")
    public String getHouses(Model model) {
        List<House> houses = houseService.getAll();
        Map<Integer, List<String>> imagesPerHouse = new HashMap<>();
        for (House house : houses) {
            System.out.println("the id "+house.getId());
            List<byte[]> imageBytesList = house.getImageData();
            List<String> base64Images = imageBytesList.stream()
                    .map(imageData -> Base64.getEncoder().encodeToString(imageData))
                    .collect(Collectors.toList());
            imagesPerHouse.put(house.getId(), base64Images);
        }

        model.addAttribute("houses", houses);
        model.addAttribute("images", imagesPerHouse);

        return "houses.html";
    }

    @GetMapping("/{id}")
    public String getHouse(@PathVariable int id, Model model) {
        model.addAttribute("house", houseService.get(id));
        return "updateListing";
    }

    @GetMapping("/view/{id}")
    public String viewHouse(@PathVariable int id, Model model) {
        House house = houseService.get(id);
        List<byte[]> imageBytesList = house.getImageData(); // Assuming House has List<byte[]> imageData

        List<String> base64Images = imageBytesList.stream()
                .map(imageData -> Base64.getEncoder().encodeToString(imageData))
                .collect(Collectors.toList());

        model.addAttribute("house", house);
        model.addAttribute("images", base64Images);
        return "viewHouse";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("house", new House());
        return "createHouse";
    }

    @PostMapping("/create")
    public String createHouse(@ModelAttribute House house, @RequestParam("images") List<MultipartFile> images, Model model) {
        try {
            System.out.println("house controller");
            houseService.save(house, images);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", "Error saving house. Please try again.");
            return "createHouse";
        }
        return "redirect:/house/all";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute House house) {
        houseService.update(id, house);
        return "redirect:/house/all";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable Integer id) {
        houseService.delete(id);
        return "redirect:/house/all";
    }
}
