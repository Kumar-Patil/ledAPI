package com.ledVan.repository;

import com.ledVan.model.District;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DistrictRepository extends CrudRepository<District, Long> {

    @Query("select count(e) from District e")
    long districtCount();

    @Query("select count(e) from District e where e.id=:districtId")
    long districtCount(@Param("districtId") long districtId);

}
