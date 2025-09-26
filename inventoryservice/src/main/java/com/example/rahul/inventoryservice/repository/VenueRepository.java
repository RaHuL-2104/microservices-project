package com.example.rahul.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.rahul.inventoryservice.entity.Venue;

@Repository
public interface VenueRepository extends JpaRepository<Venue, Long>{

}
