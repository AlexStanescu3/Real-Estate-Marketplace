package edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Apartment;

import edu.scoalainformala.stanescu_alexandru_emanuel.Listing.Apartment.Apartment;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends JpaRepository<Apartment, Integer> {

}
