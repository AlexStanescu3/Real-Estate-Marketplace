package edu.scoalainformala.stanescu_alexandru_emanuel.Listing.House;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class HouseService {

    public final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public List<House> houses = new ArrayList<>();

    {
    }

    public List<House> getAll() {
        return getHouses();
    }

    public List<House> getHouses() {
        return houseRepository.findAllByOrderByIdDesc();
    }

    public House get(int id) {
        House house = houseRepository.findAll().stream()
                .filter(h -> h.getId() == id)
                .findFirst()
                .get();

        return house;
    }

    public void save(House house, List<MultipartFile> imageFiles) throws IOException {
        System.out.println("house service");
        List<byte[]> images = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            images.add(file.getBytes());
        }
        house.setImageData(images);
        house.setId(getGreatestId() + 1);

        System.out.println("house service "+house.getId());
        houses.add(house);
        System.out.println("house service "+houses);
        houseRepository.saveAll(houses);

    }

    public void update(Integer id, House updateHouse) {
        final House house = houseRepository.findById(id).stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .get();

        house.setPrice(updateHouse.getPrice());
        house.setTitle(updateHouse.getTitle());
        house.setDescription(updateHouse.getDescription());
        house.setPhoneNumber(updateHouse.getPhoneNumber());
    }

    public void delete(Integer id) {
        houseRepository.deleteById(id);

    }

    private int getGreatestId() {
        System.out.println("repo "+houseRepository.findAll());
        if (houseRepository.findAll().isEmpty()) {
            return 0;
        } else {
            return houseRepository.findAll().stream()
                    .sorted(Comparator.comparingInt(House::getId).reversed())
                    .findFirst()
                    .get()
                    .getId();
        }
    }

}
