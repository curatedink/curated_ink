package com.curatedink.services;


import com.curatedink.models.User;
import com.curatedink.models.UserWithRoles;
import com.curatedink.repositories.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsLoader implements UserDetailsService {

    // Dependency Injection:
    private final UserRepo users;

    public UserDetailsLoader(UserRepo users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        }

        return new UserWithRoles(user);
    }
}
