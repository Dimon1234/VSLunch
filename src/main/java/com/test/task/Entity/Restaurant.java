package com.test.task.Entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Restaurant")
public class Restaurant implements Serializable{

    @OneToMany(mappedBy = "restaurant")
    private List<Dish> dishList = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "Restaurant_ID")
    private int id;

    @Column(name = "REST_NAME", length = 200)
    private String restaurantName;

    @Column(name = "VOTES")
    private int votesCount;

    public Restaurant() {
    }

    public Restaurant(String restaurantName, int votesCount) {
        this.restaurantName = restaurantName;
        this.votesCount = votesCount;
    }

    public Restaurant(Set<Dish> dishesSet, String restaurantName, int votesCount) {
        this.dishList = dishList;
        this.restaurantName = restaurantName;
        this.votesCount = votesCount;

    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "dishList=" + dishList +
                ", id=" + id +
                ", restaurantName='" + restaurantName + '\'' +
                ", votesCount=" + votesCount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public int getVotesCount() {
        return votesCount;
    }

    public void setVotesCount(int votesCount) {
        this.votesCount = votesCount;
    }
}