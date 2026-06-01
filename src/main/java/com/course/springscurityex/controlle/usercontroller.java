package com.course.springscurityex.controlle;


import com.course.springscurityex.model.users;
import com.course.springscurityex.service.userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
