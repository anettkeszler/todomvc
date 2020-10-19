package com.example.todomvc.controller;

import com.example.todomvc.repository.TodoRepository;
import com.example.todomvc.service.TodoAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.web.bind.annotation.RestController
@CrossOrigin("https://localhost:8080/")
public class RestController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoAPIService todoAPIService;







}
