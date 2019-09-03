package com.ledVan.repository;

import com.ledVan.model.PanelUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface PanelUserRepository extends CrudRepository<PanelUser, Long>{

    @Query("select count(e) from PanelUser e")
    long panelUserCount();
}
