package edu.scoalainformala.stanescu_alexandru_emanuel.Listing.House;

import edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Listing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "house")


public class House extends Listing {
    public int floors;
    public int landSqm;
    @ElementCollection
    @CollectionTable(name = "listing_image_dataHouse", joinColumns = @JoinColumn(name = "listing_id"))
    @Column(name = "image_data")
    @JdbcTypeCode(Types.LONGVARBINARY)
    public List<byte[]> imageData;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return Objects.equals(id, house.id);
    }

    @Override
    public int hashCode() {
        return id;
    }
}
