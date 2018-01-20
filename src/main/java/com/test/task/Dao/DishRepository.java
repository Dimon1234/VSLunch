package com.test.task.Dao;


import com.test.task.Entity.Dish;
import com.test.task.Entity.Menu;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface DishRepository extends JpaRepository<Dish,Integer> {
    @CacheEvict(value = "menus", allEntries = true)
    @Secured(value = "ROLE_ADMIN")
    Dish save(Dish dish);

    @CacheEvict(value = "menus", allEntries = true)
    @Secured(value = "ROLE_ADMIN")
    void delete(Integer id);

    @RestResource(path = "by-menu")
    @Transactional(readOnly = true)
    @Query("SELECT d FROM Dish d WHERE d.menu=:menu")
    List<Dish> findByMenu(@Param("menu") Menu menu);



}
