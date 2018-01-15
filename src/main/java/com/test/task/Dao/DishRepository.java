package com.test.task.Dao;


import com.test.task.Entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.annotation.Secured;


public interface DishRepository extends JpaRepository<Dish,Integer> {

    @Secured(value = "ROLE_ADMIN")
    Dish save(Dish dish);

    @Secured(value = "ROLE_ADMIN")
    void delete(Integer id);


}
