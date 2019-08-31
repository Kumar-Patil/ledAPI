package com.ledVan.repository;

import com.ledVan.model.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{

    @Query("select count(e) from District e")
    long districtCount();
}
