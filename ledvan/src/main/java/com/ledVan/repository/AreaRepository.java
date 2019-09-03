package com.ledVan.repository;

import com.ledVan.model.AreaDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AreaRepository extends CrudRepository<AreaDetails, Long>{

    @Query("select count(e) from AreaDetails e")
    long areaCount();
}
