package com.example.todomvc;

import com.example.todomvc.model.Status;
import com.example.todomvc.model.Todo;
import com.example.todomvc.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TodomvcApplication {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private Todo todo;

    public static void main(String[] args) {
        SpringApplication.run(TodomvcApplication.class, args);
    }

    @Bean
    public CommandLineRunner init() {
        CommandLineRunner clr = args -> {
            Todo todo1 = Todo.builder()
                    .status(Status.ACTIVE)
                    .title("first TODO item")
                    .build();

            Todo todo2 = Todo.builder()
                    .title("second TODO item")
                    .status(Status.ACTIVE)
                    .build();

            Todo todo3 = Todo.builder()
                    .title("third TODO item")
                    .status(Status.ACTIVE)
                    .build();

            todoRepository.save(todo1);
            todoRepository.save(todo2);
            todoRepository.save(todo3);
        };
        return clr;
    }

}
