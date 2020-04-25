package com.ledVan.repository;

import com.ledVan.model.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface AdminRepository extends CrudRepository<Admin, Long>{

    @Query("select count(e) from Admin e")
    long userCount();
    
    @Query("select u from Admin u where u.email=:email AND u.password=:password")
    public Admin getUser(@Param("email") String email, @Param("password") String userPassword);
    
    @Query("select count(e) from Admin e where e.id=:id")
    long userCount(@Param("id") long id);
}
