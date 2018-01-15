package com.test.task.Entity;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "rest_name"}, name = "unique_id")})
public class Restaurant implements Serializable {


    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "restaurant_id")
    private int id;

    @Column(name = "rest_name", length = 200, nullable = false)
    private String restaurantName;





    public Restaurant() {
    }

    public Restaurant(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Restaurant(String restaurantName, Menu menu) {
        this.restaurantName = restaurantName;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", restaurantName='" + restaurantName + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }


}