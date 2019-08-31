package com.ledvan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ledvan.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.userName=:userName AND u.userPassword=:userPassword")
    public User getUser(@Param("userName") String userName, @Param("userPassword") String userPassword);
}
