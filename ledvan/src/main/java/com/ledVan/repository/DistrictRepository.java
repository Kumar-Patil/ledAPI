package com.ledVan.repository;

import com.ledVan.model.District;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface DistrictRepository extends CrudRepository<District, Long>{

    @Query("select count(e) from District e")
    long districtCount();
}
