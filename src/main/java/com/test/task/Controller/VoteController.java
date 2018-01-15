package com.test.task.Controller;

import com.test.task.Dao.MenuRepository;
import com.test.task.Entity.Menu;
import com.test.task.Entity.Restaurant;
import com.test.task.Entity.Vote;
import com.test.task.Service.UserDetailsService;
import com.test.task.Service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.time.LocalTime;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/vote", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    public static final LocalTime EXPIRED_TIME = LocalTime.parse("13:00");

    @Autowired
    private VoteService voteService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MenuRepository menuRepository;

    @GetMapping
    public String currentMenus() {
        StringBuilder builder = new StringBuilder();
        for (Menu menu : menuRepository.findAll()) {
            builder.append(menu.toString()).append("\n");
        }
        return builder.toString();
    }


    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<Restaurant> vote(@PathVariable("id") Integer id, Principal user) {

        Menu menu = menuRepository.findOne(id);
        if (menu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        int userId = userDetailsService.loadUserByUsername(user.getName()).getId();

        boolean expired = LocalTime.now().isAfter(EXPIRED_TIME);
        Vote vote;
        if (!expired) {
            vote = voteService.saveVote(userId, menu);
            return new ResponseEntity<>(vote.getStatus() ? HttpStatus.CREATED : HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);


    }


}
