package com.course.springscurityex.service;

import com.course.springscurityex.model.users;
import com.course.springscurityex.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class userservice {

    @Autowired
    private Userrepo userrepo;

    @Autowired
    private JWTservice JWTservice;

    @Autowired
    private AuthenticationManager manager;

    public users register(users user) {
        return userrepo.save(user);
    }

    public String verfiy(users user) {
        try {
            Authentication authentication = manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );

            if (authentication.isAuthenticated()) {
                return JWTservice.jwgenerateToken(authentication.getName());
            }
        } catch (AuthenticationException exception) {
            throw new BadCredentialsException("Invalid username or password", exception);
        }

        throw new BadCredentialsException("Invalid username or password");
    }
}
