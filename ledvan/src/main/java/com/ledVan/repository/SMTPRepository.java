package com.ledVan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ledVan.model.SMTPDetails;

@Repository
public interface SMTPRepository extends JpaRepository<SMTPDetails, Long>{

}
