package edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Apartment;

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
public class ApartmentService {


    public final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> apartments = new ArrayList<>();

    {
    }

    public List<Apartment> getAll() {
        return getApartments();
    }

    public List<Apartment> getApartments() {
        return apartmentRepository.findAllByOrderByIdDesc();
    }

    public Apartment get(int id) {
        Apartment ap = apartmentRepository.findAll().stream()
                .filter(apartment -> apartment.getId() == id)
                .findFirst()
                .get();

        return ap;
    }

    public void save(Apartment apartment, List<MultipartFile> imageFiles) throws IOException {
        System.out.println(" i end up here");
        List<byte[]> images = new ArrayList<>();
        for (MultipartFile file : imageFiles) {
            images.add(file.getBytes());
        }
        apartment.setImageData(images);
        apartment.setId(getGreatestId() + 1);
        apartments.add(apartment);
        apartmentRepository.saveAll(apartments);
    }


    public void update(Integer id, Apartment updateApartment) {

        final Apartment apartment = apartmentRepository.findById(id).stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .get();

        apartment.setPrice(updateApartment.getPrice());
        apartment.setTitle(updateApartment.getTitle());
        apartment.setDescription(updateApartment.getDescription());
        apartment.setPhoneNumber(updateApartment.getPhoneNumber());
    }


    public void delete(Integer id) {

        apartmentRepository.deleteById(id);
    }

    private int getGreatestId() {
        if (apartmentRepository.findAll().isEmpty()) {
            return 0;
        } else {
            return apartmentRepository.findAll().stream()
                    .sorted(Comparator.comparingInt(Apartment::getId).reversed())
                    .findFirst()
                    .get()
                    .getId();
        }


    }

}
