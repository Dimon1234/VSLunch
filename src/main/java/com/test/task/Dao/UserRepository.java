package com.test.task.Dao;


import com.test.task.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Integer> {

    @RestResource(path = "/users")
    List<User> findAll();

    @Query("select u from User u where u.email=:email")
    User findByEmail(@Param("email") String email);

    @Query("select u from User u where u.name=:name")
    User findByName(@Param("name") String name);
}
