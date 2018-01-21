package com.test.task.Service;

import com.test.task.Dao.RestaurantRepository;
import com.test.task.Entity.Restaurant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class RestaurantService {
    private static final Logger LOG = LogManager.getLogger(RestaurantService.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Transactional
    @CacheEvict(value = "menus", allEntries = true)
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        LOG.info("restaurant "+restaurant.getName()+" saved");
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void delete(Integer id)
    {
        if (restaurantRepository.findOne(id) != null){
            LOG.info("restaurant with id "+id+" deleted");
            restaurantRepository.delete(id);
        }

    }

    @CacheEvict(value = "menus", allEntries = true)
    public void update(String name, Integer id)
    {
        Assert.notNull(name, "name must not be null");
        if (restaurantRepository.findOne(id) != null)
        {
            restaurantRepository.updateRestName(id,name);
            LOG.info("restaurant with id "+id+" was updated with name "+name);
        }
        Assert.notNull(id,"restaurant with such id doesn't exist");
    }
    public Restaurant findOne(Integer id)
    {
        return restaurantRepository.findOne(id);
    }

    public List<Restaurant> findAll()
    {
        return restaurantRepository.findAll();
    }

}
