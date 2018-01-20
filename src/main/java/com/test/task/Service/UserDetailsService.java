package com.test.task.Service;

import com.test.task.Dao.UserRepository;
import com.test.task.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private static final Logger LOG = LogManager.getLogger(UserDetailsService.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public LoggedUser loadUserByUsername(final String name) {
        String lowercaseLogin = name.toLowerCase();
        User user = userRepository.findByName(lowercaseLogin);
        if (user == null) {
            String err = "User " + lowercaseLogin + " was not found in the database";
            LOG.error(err);
            throw new UsernameNotFoundException(err);
        }

        return new LoggedUser(user);
    }
}
