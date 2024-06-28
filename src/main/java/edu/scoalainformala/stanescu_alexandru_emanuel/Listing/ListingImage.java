package edu.scoalainformala.stanescu_alexandru_emanuel.Listing;

import edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Apartment.Apartment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.sql.Types;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListingImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @JdbcTypeCode(Types.LONGVARBINARY)
    public byte[] imageData;

 //   @ManyToOne(fetch = FetchType.LAZY)
   // public Listing listing;
    public String imageName;
    public String imageType;

    // Constructors, getters, setters, etc.
}
