package com.ledVan.repository;

import com.ledVan.model.RouteMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RouteMapRepository extends JpaRepository<RouteMap, Long>{

}
