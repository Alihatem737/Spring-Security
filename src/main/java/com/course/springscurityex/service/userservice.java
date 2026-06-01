package com.course.springscurityex.service;

import com.course.springscurityex.model.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.course.springscurityex.repo.Userrepo;


@Service
public class userservice {


    @Autowired
    private Userrepo userrepo;

    public users register(users user){
         return userrepo.save(user);
    }
}
