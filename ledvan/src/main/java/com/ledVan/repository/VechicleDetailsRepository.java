package com.ledVan.repository;

import com.ledVan.model.VehicleDetails;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface VechicleDetailsRepository extends CrudRepository<VehicleDetails, Long> {

    @Query("select count(e) from VehicleDetails e")
    long vechicleCount();
    
    @Query("select count(e) from VehicleDetails e where e.districtId=:districtId")
    long vechicleCount(@Param("districtId") long districtId);
    
    @Query("select e from VehicleDetails e where e.districtId=:districtId")
    List<VehicleDetails> vechicleDetails(@Param("districtId") long districtId);
}
