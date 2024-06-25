package edu.scoalainformala.stanescu_alexandru_emanuel.Listing;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class Listing {
    @Id
    @SequenceGenerator(
            name = "listing_sequence",
            sequenceName = "listing_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "listing_sequence"
    )

    public Integer id;
    public String title;
    public String description;
    public Integer price;
    public String address;
    public int rooms;
    public int sqm;
    public boolean ForSale;
    public boolean ForLease;
    public String ownerName;
    public String phoneNumber;
    @ElementCollection
    @CollectionTable(name = "listing_images", joinColumns = @JoinColumn(name = "listing_id"))
    @Column(name = "image_data")
    public List<byte[]> images;

}
