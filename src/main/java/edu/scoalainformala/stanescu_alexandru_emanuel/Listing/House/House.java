package edu.scoalainformala.stanescu_alexandru_emanuel.Listing.House;

import edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Listing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class House extends Listing {
    public int landSqm;


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
