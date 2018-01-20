package com.test.task.Service;

import com.test.task.Dao.DishRepository;
import com.test.task.Dao.MenuRepository;
import com.test.task.Dao.RestaurantRepository;
import com.test.task.Dao.UserRepository;
import com.test.task.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InitService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DishRepository dishRepository;

    @PostConstruct
    public void initUsers()
    {
        Set<Role> rolesAdmin = new HashSet<>();
        rolesAdmin.add(Role.ROLE_ADMIN);
        rolesAdmin.add(Role.ROLE_USER);

        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(Role.ROLE_USER);

        userRepository.save(new User("admin", "123456", "admin@mail.ru", rolesAdmin));
        userRepository.save(new User("user","123456","email@mail.ru",rolesUser));


    }

    @PostConstruct
    public void initRestaurants()
    {
        Restaurant r = new Restaurant("dve palochki");
        restaurantRepository.save(r);
        Menu menu = new Menu(r,LocalDate.parse("2018-01-20"));
        menuRepository.save(menu);
        List<Dish> listDish = new ArrayList<>();
        Dish dish1 = new Dish(menu, "desert", 100d);
        Dish dish2 = new Dish(menu, "pervoe", 200d);
        Dish dish3 = new Dish(menu, "vtoroe", 300d);
        listDish.add(dish1);
        listDish.add(dish2);
        listDish.add(dish3);

        dishRepository.save(listDish);


    }
}
