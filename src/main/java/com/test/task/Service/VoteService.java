package com.test.task.Service;

import com.test.task.Dao.UserRepository;
import com.test.task.Dao.VoteRepository;
import com.test.task.Entity.Menu;
import com.test.task.Entity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class VoteService {


    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @CacheEvict(value = "menus", allEntries = true)
    @Transactional
    public Vote saveVote(int user_id, Menu menu) {
        LocalDate date = menu.getDate();
        Vote vote = voteRepository.getForUserAndDate(user_id, date)
                .map(v -> {
                    v.setMenu(menu);
                    v.setStatus(false);
                    return v;
                })
                .orElseGet(() ->
                        new Vote(userRepository.getOne(user_id), menu, date, true));
        voteRepository.save(vote);
        return vote;
    }

}
