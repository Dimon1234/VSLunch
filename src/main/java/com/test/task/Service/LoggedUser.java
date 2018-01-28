package com.test.task.Service;

import com.test.task.Entity.Role;
import com.test.task.Entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;

import static java.util.Objects.requireNonNull;


public class LoggedUser extends org.springframework.security.core.userdetails.User {

    private static Logger LOG = LogManager.getLogger(LoggedUser.class);
    private int id;
    private Set<Role> roleSet;

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(), true, true, true,
                true, user.getRoleSet());
        this.id = user.getId();
        this.roleSet = user.getRoleSet();
    }

    private static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();

        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    private static LoggedUser get() {
        LoggedUser user = safeGet();
        String message = "No authorized user found";
        requireNonNull(user, message);
        LOG.error(message);
        return user;
    }

    Set<Role> getRoleSet() {
        return roleSet;
    }

    public static int id() {
        return get().id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User (" + id + ',' + getUsername() + ")";
    }
}