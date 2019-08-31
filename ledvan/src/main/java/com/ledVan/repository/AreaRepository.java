package com.ledVan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ledVan.model.AreaDetails;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AreaRepository extends JpaRepository<AreaDetails, Long>{

    @Query("select count(e) from AreaDetails e")
    long areaCount();
}
