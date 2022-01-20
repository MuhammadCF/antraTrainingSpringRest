package com.example.antraTrainingSpringRest.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class StudentController {

    @RequestMapping("/")
    public String home()
    {
        return "home.jsp";
    }
}
