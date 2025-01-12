package com.todoapp.todo_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class DemoController {

    @GetMapping("/")
    public String demo() {
        return "To-Do RESTful API";
    }
}
