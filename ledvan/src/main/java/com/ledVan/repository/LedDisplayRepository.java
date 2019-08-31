package com.ledVan.repository;

import com.ledVan.model.LedDisplay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LedDisplayRepository extends JpaRepository<LedDisplay, Long> {

    @Query("select count(e) from LedDisplay e where e.status=:status")
    long ledDisplayCount(@Param("status") String status);
}
