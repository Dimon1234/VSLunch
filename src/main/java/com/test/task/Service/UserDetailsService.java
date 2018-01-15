package com.test.task.Service;

import com.test.task.Dao.UserRepository;
import com.test.task.Entity.User;
import com.test.task.LoggedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public LoggedUser loadUserByUsername(final String name) {
        String lowercaseLogin = name.toLowerCase();
        log.debug("Authenticating: ", name);
        User user = userRepository.findByName(lowercaseLogin);
        if (user == null) {
            throw new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database");
        }

        return new LoggedUser(user);
    }
}
