package com.course.springscurityex.controlle;


import com.course.springscurityex.model.users;
import com.course.springscurityex.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class usercontroller {

    @Autowired
    private userservice userservice;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @PostMapping("/register")
    public users register(@RequestBody users user){
        user.setPassword(encoder.encode(user.getPassword()));
       return userservice.register(user);
    }


    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody users user){
        String token = userservice.verfiy(user);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handleBadCredentials() {
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
