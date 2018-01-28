package com.test.task.Service;

import com.test.task.Dao.DishRepository;
import com.test.task.Entity.Dish;
import com.test.task.Entity.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class DishService {
    private static final Logger LOG = LogManager.getLogger(DishService.class);

    @Autowired
    private DishRepository dishRepository;

    @Transactional
    @CacheEvict(value = "menus", allEntries = true)
    public Dish save(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        LOG.info("dish " + dish + " saved");
        return dishRepository.save(dish);
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void delete(Integer id) {
        if (dishRepository.findOne(id) != null) {
            LOG.info("dish with id " + id + " deleted");
            dishRepository.delete(id);
        }

    }


    public List<Dish> findByMenu(Menu menu) {
        return dishRepository.findByMenu(menu);
    }

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }
}
