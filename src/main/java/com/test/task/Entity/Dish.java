package com.test.task.Entity;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Table(name = "Dish")
public class Dish implements Serializable {


    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "dish_id")
    private int id;



    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "menu_id")
    private Menu menu;


    @Column(name = "dish_name", nullable = false)
    private String dishName;


    @Column(name = "price", nullable = false)
    private double price;

    public Dish() {
    }

    public Dish(Menu menu, String dishName, double price) {
        this.menu = menu;
        this.dishName = dishName;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
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
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                '}';
    }
}
