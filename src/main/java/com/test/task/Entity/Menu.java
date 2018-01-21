package com.test.task.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "Menu", uniqueConstraints = {@UniqueConstraint(columnNames = {"Date", "Restaurant_id"}, name = "unique_menu")})
public class Menu {
    @Id
    @Column(name = "Menu_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Date", nullable = false)
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "Restaurant_id")
    @NotNull
    private Restaurant restaurant;

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "menu")
    @Column(name = "dishes")
    private List<Dish> dishList;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "menu")
    @Column(name = "votes")
    private List<Vote> votes;

    public Menu() {
    }

    public Menu(Restaurant restaurant, LocalDate date) {
        this.restaurant = restaurant;
        this.date = date;

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

    public int getVotesCount() {
        return votes == null ? 0 : votes.size();
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", date=" + date +
                ", restaurant=" + restaurant +
                ", dishList=" + dishList +
                ", votes=" + getVotesCount() +
                '}';
    }
}
