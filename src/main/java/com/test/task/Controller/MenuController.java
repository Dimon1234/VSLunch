package com.test.task.Controller;

import com.test.task.Entity.Menu;
import com.test.task.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/menus", produces = MediaType.APPLICATION_JSON_VALUE)

public class MenuController {

    @Autowired
    private MenuService service;

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public Menu getOne(@PathVariable("id") int id) {
        return service.findOne(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> getAll() {
        return service.findAll();
    }


    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody LocalDate date, @PathVariable("id") int id) {
        service.updateMenuDate(id, date);
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@RequestBody Resource<Menu> menu) {

        Menu created = service.save(menu.getContent());
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
