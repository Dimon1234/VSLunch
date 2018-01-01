package com.test.task.Entity;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Dish")
public class Dish implements Serializable {

   @ManyToOne
   @JoinColumn(name = "Restaurant_ID", insertable = false, updatable=false)
    private Restaurant restaurant;

    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "DISH_ID")
    private int id;

    @Column(name = "MENU_ID")
    private int menu_id;

    @Column(name = "DISH_NAME")
    private String dishName;


    @Column(name = "PRICE")
    private double price;

    public Dish() {
    }

    public Dish(Restaurant restaurant, String dishName, double price) {
        this.restaurant = restaurant;
        this.dishName= dishName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "restaurant=" + restaurant +
                ", id=" + id +
                ", menu_id=" + menu_id +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                '}';
    }
}
