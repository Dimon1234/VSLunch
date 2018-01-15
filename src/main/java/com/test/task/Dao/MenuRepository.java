package com.test.task.Dao;

import com.test.task.Entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;


public interface MenuRepository extends JpaRepository<Menu,Integer> {

    Menu findOne(Integer id);

    @Secured(value = "ROLE_ADMIN")
    Menu save(Menu menu);

    @Secured(value = "ROLE_ADMIN")
    void delete(Integer id);


}
