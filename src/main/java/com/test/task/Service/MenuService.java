package com.test.task.Service;

import com.test.task.Dao.MenuRepository;
import com.test.task.Entity.Menu;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.util.List;

@Service
public class MenuService {

    private static Logger LOG = LogManager.getLogger(MenuService.class);

    @Autowired
    private MenuRepository menuRepository;

    @Cacheable("menus")
    public List<Menu> findAll() {

        return menuRepository.findAll();
    }

    public Menu findOne(Integer id) {
        return menuRepository.findOne(id);
    }

    @Transactional
    @CacheEvict(value = "menus", allEntries = true)
    public Menu save(Menu menu) {
        Assert.notNull(menu, "menu must not be null");
        LOG.info("menu " + menu + " was saved in database");
        return menuRepository.save(menu);
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void delete(Integer id) {
        Assert.notNull(menuRepository.findOne(id), "menu with such id doesn't exist");
        LOG.info("menu with id = " + id + " was deleted");
        menuRepository.delete(id);
    }

    @CacheEvict(value = "menus", allEntries = true)
    public void updateMenuDate(Integer id, LocalDate date) {
        Assert.notNull(findOne(id), "menu with such id doesn't exist");
        Assert.notNull(date, "date must not be null");
        LOG.info("menu with id " + id + " updated with date " + date);
        menuRepository.updateDate(id, date);
    }
}
