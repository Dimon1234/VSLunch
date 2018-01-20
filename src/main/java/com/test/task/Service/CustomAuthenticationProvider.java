package com.test.task.Service;

import com.test.task.Entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOG = LogManager.getLogger(CustomAuthenticationProvider.class);
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    @Autowired
    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Transactional
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        LoggedUser user;

        if ((user = userDetailsService.loadUserByUsername(name)) != null) {
            if (encoder.matches(password, user.getPassword())) {
                LOG.info("user " + user.getUsername() +
                        " authorized with role " +
                        (user.getRoleSet().contains(Role.ROLE_ADMIN) ? Role.ROLE_ADMIN.name() : Role.ROLE_USER.name())
                );
                return new UsernamePasswordAuthenticationToken(
                        name, password, user.getRoleSet());
            } else {
                LOG.error("user "+user.getUsername()+" with wrong password");
                return null;
            }
        } else {
            LOG.error("user with name "+user.getUsername()+" does not exist");
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
