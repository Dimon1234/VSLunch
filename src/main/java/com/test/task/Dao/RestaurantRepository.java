package com.test.task.Dao;

import com.test.task.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.annotation.Secured;

@RestResource
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer> {

    @Secured(value = "ROLE_ADMIN")
    Restaurant save(Restaurant restaurant);

    @Secured(value = "ROLE_ADMIN")
    void delete(Integer id);

}
