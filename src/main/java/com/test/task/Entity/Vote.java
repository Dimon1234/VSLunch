package com.test.task.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "vote_date"}, name = "unique_vote")})
public class Vote {

    @Id
    @NotNull
    @Column(name = "vote_id")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    @NotNull
    private Menu menu;

    @NotNull
    @Column(name = "vote_date", nullable = false)
    private LocalDate date;

    @Column(name = "status")
    private boolean status;

    public Vote() {
    }

    public Vote(User user, Menu menu, LocalDate date, boolean status) {
        this.user = user;
        this.menu = menu;
        this.date = date;
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                '}';
    }
}
