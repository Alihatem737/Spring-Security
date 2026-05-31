package com.course.springscurityex.controlle;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class hellocontroller {

    @GetMapping("/")
    public String greet(HttpServletRequest request){
return "welcome to course" + request.getSession().getId();
    }

}
