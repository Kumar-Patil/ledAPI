package com.ledVan.repository;

import com.ledVan.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PanelUserRepository extends CrudRepository<Admin, Long> {

    @Query("select count(e) from Admin e where e.roleId=3")
    long panelUserCount();

    @Query("select count(e) from PanelUser e where e.districtId=:districtId")
    long panelUserCount(@Param("districtId") long districtId);
}
