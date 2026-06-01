package com.course.springscurityex.service;

import com.course.springscurityex.model.users;
import com.course.springscurityex.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.course.springscurityex.model.UserPrincipal;
import org.springframework.stereotype.Service;


@Service
public class MyUserdetailssevice implements UserDetailsService {


    @Autowired
    private Userrepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        users user = userrepo.findByUsername(username);

        if (user==null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }


        return new UserPrincipal(user) ;
    }
}
