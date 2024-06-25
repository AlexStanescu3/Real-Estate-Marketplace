package edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Apartment;

import edu.scoalainformala.stanescu_alexandru_emanuel.Util.FileUploadUtil;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.events.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.zip.DataFormatException;

@Service
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
        return apartmentRepository.findAll();
    }

    public Apartment get(int id) {
        Apartment ap = apartmentRepository.findAll().stream()
                .filter(apartment -> apartment.getId() == id)
                .findFirst()
                .get();

        return ap;
    }

    public List<Apartment> save(Apartment apartment, MultipartFile imageFiles) throws IOException {
        List<byte[]> images = new ArrayList<>();
        /*for (MultipartFile file : imageFiles) {
            images.add(file.getBytes());
        }*/
        apartment.setImageData(FileUploadUtil.compressImage(imageFiles.getBytes()));
        apartment.setImageName(imageFiles.getOriginalFilename());
        apartment.setImageType(imageFiles.getContentType());
        apartment.setId(getGreatestId() + 1);
        apartments.add(apartment);
        return apartmentRepository.saveAll(apartments);
    }

    public byte[] downloadImage(Integer id) {
        Optional<Apartment> apartmentRepo = apartmentRepository.findById(id);

        return apartmentRepo.map(apartment -> {
            try {

                return FileUploadUtil.decompressImage(apartment.getImageData());
            } catch (DataFormatException | IOException exception) {
                throw new ContextedRuntimeException("Error downloading an image", exception)
                        .addContextValue("ap ID",  apartment.getId())
                        .addContextValue("aaap name", id);
            }
        }).orElse(null);
    }


//    @Transactional
//    public void save(Apartment apartment) throws IOException {
//
//        if (apartment.getImageFile() != null && !apartment.getImageFile().isEmpty()) {
//            byte[] imageData = apartment.getImageFile().getBytes();
//            apartment.setImages(imageData);
//        }
//
//        apartment.setId(getGreatestId() + 1);
//        apartments.add(apartment);
//        apartmentRepository.saveAll(apartments);
//    }

//    public void add(Apartment apartment) {
//
//        apartment.setId(getGreatestId() + 1);
//        apartments.add(apartment);
//        apartmentRepository.saveAll(apartments);
//    }

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
        apartments = apartments.stream()
                .filter(user -> !user.getId().equals(id))
                .collect(Collectors.toList());
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
