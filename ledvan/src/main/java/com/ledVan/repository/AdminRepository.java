package com.ledVan.repository;

import com.ledVan.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface AdminRepository extends CrudRepository<Admin, Long>{

    @Query("select count(e) from Admin e")
    long userCount();
    
    @Query("select u from Admin u where u.userName=:name AND u.password=:password")
    public Admin getUser(@Param("name") String userName, @Param("password") String userPassword);
}
