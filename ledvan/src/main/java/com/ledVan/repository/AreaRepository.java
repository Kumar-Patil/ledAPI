package com.ledVan.repository;

import com.ledVan.model.AreaDetails;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AreaRepository extends CrudRepository<AreaDetails, Long>{

    @Query("select count(e) from AreaDetails e")
    long areaCount();
    
    @Query("select count(e) from AreaDetails e where e.districtId=:districtId")
    long areaCount(@Param("districtId") long districtId);
    
    @Query("select e from AreaDetails e where e.districtId=:districtId")
    List<AreaDetails> getListOfAreas(@Param("districtId") long districtId);
}
