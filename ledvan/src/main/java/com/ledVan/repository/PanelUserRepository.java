package com.ledVan.repository;

import com.ledVan.model.PanelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface PanelUserRepository extends JpaRepository<PanelUser, Long>{

    @Query("select count(e) from PanelUser e")
    long panelUserCount();
}
