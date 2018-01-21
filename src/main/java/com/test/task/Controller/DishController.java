package com.test.task.Controller;

import com.test.task.Entity.Dish;
import com.test.task.Service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/dishes", produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {

    @Autowired
    private DishService service;

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @GetMapping
    public List<Dish> getAll() {
        return service.findAll();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Resource<Dish> dish) {
        Dish created = service.save(dish.getContent());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dishes/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
