package com.ledVan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ledVan.model.VehicleDetails;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface VechicleDetailsRepository extends JpaRepository<VehicleDetails, Long> {

    @Query("select count(e) from VehicleDetails e")
    long vechicleCount();
}
