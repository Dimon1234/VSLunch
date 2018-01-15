package com.test.task.Service;

import com.test.task.Dao.MenuRepository;
import com.test.task.Dao.RestaurantRepository;
import com.test.task.Dao.UserRepository;
import com.test.task.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuRepository menuRepository;

    @PostConstruct
    public void initUsers()
    {
        Set<Role> rolesAdmin = new HashSet<>();
        rolesAdmin.add(Role.ROLE_ADMIN);
        rolesAdmin.add(Role.ROLE_USER);

        Set<Role> rolesUser = new HashSet<>();
        rolesUser.add(Role.ROLE_USER);

        userRepository.save(new User("dimon", "123456", "wap.mmj@mail.ru", rolesAdmin));
        userRepository.save(new User("user","123456","email@mail.ru",rolesUser));
    }

    @PostConstruct
    public void initRestaurants()
    {
        Restaurant r = new Restaurant("2 keks");
        Menu menu = new Menu(r);
        ArrayList dishes = new ArrayList<Dish>(Arrays.asList(new Dish[]{new Dish(menu,"dish1",100.9), new Dish(menu,"dish2",110.2)}));
        menu.setDishList(dishes);

        Restaurant restaurant = new Restaurant("3 ses");
        Menu menu1 = new Menu(r);
        ArrayList dishes1 = new ArrayList<Dish>(Arrays.asList(new Dish[]{new Dish(menu,"dish2",10099.9), new Dish(menu,"dish3",1120.2)}));
        menu1.setDishList(dishes1);

        restaurantRepository.save(r);
        menuRepository.save(menu);
        restaurantRepository.save(restaurant);
        menuRepository.save(menu1);

    }
}
