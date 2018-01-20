package com.test.task.Dao;

import com.test.task.Entity.Menu;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu,Integer> {

    Menu findOne(Integer id);

    @RestResource(path = "by-date")
    @Transactional(readOnly = true)
    @Query("SELECT m FROM Menu m WHERE m.date=:date")
    List<Menu> findByDate(@Param("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date);

    @CacheEvict(value = "menus", allEntries = true)
    @Secured(value = "ROLE_ADMIN")
    Menu save(Menu menu);



    @CacheEvict(value = "menus", allEntries = true)
    @Secured(value = "ROLE_ADMIN")
    void delete(Integer id);


    @Cacheable("menus")
    List<Menu> findAll();

    @CacheEvict(value = "menus", allEntries = true)
    @Secured(value = "ROLE_ADMIN")
    @Modifying
    @Query("UPDATE Menu m SET m.date = :date WHERE m.id = :menuId")
    LocalDate updateDate(@Param("menuId") int id, @Param ("date") LocalDate date);

}
