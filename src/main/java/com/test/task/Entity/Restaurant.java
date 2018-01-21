package com.test.task.Entity;


import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Restaurant", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "name"}, name = "unique_id")})
public class Restaurant {

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "restaurant_id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    public Restaurant() {
    }

    public Restaurant(String restaurantName) {
        this.name = restaurantName;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", restaurantName='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}