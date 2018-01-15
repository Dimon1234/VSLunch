package com.test.task.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Menu")
public class Menu {
    @Id
    @Column(name = "Menu_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Date", nullable = false)
    private LocalDate date;


    @OneToOne
    @JoinColumn(name = "Restaurant_id")
    private Restaurant restaurant;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @Column(name = "dishes")
    private List<Dish> dishList;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    @Column(name = "votes")
    private List<Vote> votes;

    public Menu() {
    }

    public Menu(Restaurant restaurant) {
        this.date = LocalDate.now();
        this.restaurant = restaurant;
    }

    public Menu(Restaurant restaurant, List<Dish> dishList) {
        this.date = LocalDate.now();
        this.restaurant = restaurant;
        this.dishList = dishList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                ", restaurant=" + restaurant +
                ", dishList=" + dishList +
                ", votes=" + votes.size() +
                '}';
    }
}
