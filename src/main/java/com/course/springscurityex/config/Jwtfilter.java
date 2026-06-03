package com.course.springscurityex.config;

import com.course.springscurityex.service.JWTservice;
import com.course.springscurityex.service.MyUserdetailssevice;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class Jwtfilter extends OncePerRequestFilter {


    @Autowired
    private JWTservice jwtservice;


    @Autowired
    private MyUserdetailssevice userdetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authheader = request.getHeader("Authentication");
        String token = null;
        String username = null;


        if(authheader != null && authheader.startsWith("bearer") ){
            token = authheader.substring(7);
            username = jwtservice.extractUserName(token);

        }


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userdetails = userdetailsService.loadUserByUsername(username);

            if (jwtservice.vailddatetoken(token , userdetails)){
                UsernamePasswordAuthenticationToken authtoken =
                        new UsernamePasswordAuthenticationToken(userdetails, null , userdetails.getAuthorities()) ;


                authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authtoken);

            }
        }

        filterChain.doFilter(request , response);

    }
}
