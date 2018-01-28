package com.test.task.Controller;

import com.test.task.Dao.MenuRepository;
import com.test.task.Entity.Menu;
import com.test.task.Entity.Restaurant;
import com.test.task.Entity.Vote;
import com.test.task.Service.CustomAuthenticationProvider;
import com.test.task.Service.VoteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/vote", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {


    private static final Logger LOG = LogManager.getLogger(VoteController.class);


    private static final LocalTime EXPIRED_TIME = LocalTime.parse("11:00");


    private final VoteService voteService;
    private final MenuRepository menuRepository;

    @Autowired
    public VoteController(VoteService voteService, MenuRepository menuRepository) {
        this.voteService = voteService;
        this.menuRepository = menuRepository;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getRestaurantVotesCount(@PathVariable("id") int rest_id) {

        return String.format("Restaurant with id %s has %s votes",
                rest_id, voteService.getRestaurantVotesCount(rest_id));
    }


    @RequestMapping(value = "/{id}", method = POST)
    public ResponseEntity<Restaurant> vote(@PathVariable("id") Integer id) {

        Menu menu = menuRepository.findOne(id);
        if (menu == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        int userId = CustomAuthenticationProvider.getCurrentLoggedUser().getId();

        boolean expired = LocalTime.now().isAfter(EXPIRED_TIME);
        Vote vote;
        if (!expired) {
            vote = voteService.saveVote(userId, menu);
            LOG.info("vote " + vote + " saved successfully");
            return new ResponseEntity<>(vote.getStatus() ? HttpStatus.CREATED : HttpStatus.OK);
        } else {
            vote = voteService.repeatSaving(userId, menu);
            if (vote.getStatus()) LOG.info("vote " + vote + " saved successfully");
            else LOG.error("vote wasn't saved 'cause time is expired");

            return new ResponseEntity<>(vote.getStatus() ? HttpStatus.CREATED : HttpStatus.CONFLICT);
        }


    }


}
