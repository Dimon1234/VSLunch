package com.test.task.Dao;

import com.test.task.Entity.Restaurant;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;


public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @CacheEvict(value = "menus", allEntries = true)
    @Secured(value = "ROLE_ADMIN")
    Restaurant save(Restaurant restaurant);

    @CacheEvict(value = "menus", allEntries = true)
    @Secured(value = "ROLE_ADMIN")
    void delete(Integer id);

    @CacheEvict(value = "menus", allEntries = true)
    @Secured(value = "ROLE_ADMIN")
    @Transactional
    @Modifying
    @Query("UPDATE Restaurant r SET r.name = :name WHERE r.id = :restId")
    void updateRestName(@Param("restId") int companyId, @Param("name") String name);



}
