package com.test.task.Service;

import com.test.task.Dao.DishRepository;
import com.test.task.Dao.MenuRepository;
import com.test.task.Dao.RestaurantRepository;
import com.test.task.Dao.UserRepository;
import com.test.task.Entity.Role;
import com.test.task.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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

}
