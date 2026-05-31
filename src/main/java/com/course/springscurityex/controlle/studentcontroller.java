package com.course.springscurityex.controlle;


import com.course.springscurityex.model.student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class studentcontroller {


    private List<student> students = new ArrayList<>(List.of(
            new student(1 , "Ali" , 90 ),
            new student(2 , "Omar" , 80 )
    ));
    @GetMapping("/students")
    public List<student> getstudents(){

        return students;


    }

    @GetMapping("csrftoken")
    public String getcsrf(HttpServletRequest reqest){
        return (String) reqest.getAttribute("_csrf");
    }


    @PostMapping("/students")
    public student addstudent (@RequestBody student student ){
        students.add(student);
        return student;

    }

}
