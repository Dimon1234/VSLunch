package com.test.task.Dao;

import com.test.task.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

    Restaurant findById(int id);

}
