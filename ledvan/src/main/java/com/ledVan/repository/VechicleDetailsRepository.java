package com.ledVan.repository;

import com.ledVan.model.VehicleDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface VechicleDetailsRepository extends CrudRepository<VehicleDetails, Long> {

    @Query("select count(e) from VehicleDetails e")
    long vechicleCount();
}
