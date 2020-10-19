package com.example.todomvc.controller;

import com.example.todomvc.model.Status;
import com.example.todomvc.model.Todo;
import com.example.todomvc.repository.TodoRepository;
import com.example.todomvc.service.TodoAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoAPIService todoAPIService;

    // list all todos *
    @PostMapping("/list")
    public List<Todo> listAllTodosFromDB(@RequestBody String body) {
        // 1
        List<Todo> result = new ArrayList<Todo>();

        // 2 Do the filtering
        System.out.println(body);
        String status = body.split("=")[1];
        if (status.equalsIgnoreCase("active")) {


        }

        // 3
        return result;
//        System.out.println(body);
//        String status = body.split("=")[1];
//        if (status.equalsIgnoreCase("active")) {
//
//
//        }
//        return todoRepository.findAll();
    }

    // add new *
    @PostMapping("/addTodo")
    public Todo addNewTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }

    // find by id *
    @GetMapping("/todos/{id}")
    public Todo getTodoById(@PathVariable("id") String id) {
        return todoRepository.findById(Long.parseLong(id)).get();
    }

    // remove by id *
    @DeleteMapping("/todos/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
    }

    // delete completed todos *
    @DeleteMapping("/todos/completed")
    public void deleteAllCompleted() {
        List<Todo> allTodos = todoRepository.findAll();
        allTodos.forEach(todo -> {
            if (todo.isCompleted()) {
                todoRepository.delete(todo);
            }
        });
    }

    // toggle all status *
    @PutMapping("/todos/toggle_all")
    public void toggleAll(@RequestParam("toggle_all") boolean makeComplete) {
        List<Todo> allTodos = todoRepository.findAll();
        allTodos.forEach(todo -> {
            if (makeComplete) {
                todo.setStatus(Status.COMPLETE);
            }
            else {
                todo.setStatus(Status.ACTIVE);
            }
            todoRepository.save(todo);
        });
    }

    // Toggle status by id
    @PutMapping("/todos/{id}/toggle_status")
    public void toggleStatusById(@RequestParam("status") boolean makeComplete, @PathVariable("id") Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "todo with id " + id + " does not exist.");
        }
        Todo todo = todoOptional.get();
        if (makeComplete) {
            todo.setStatus(Status.COMPLETE);
        }
        else {
            todo.setStatus(Status.ACTIVE);
        }
        todoRepository.save(todo);
    }


   // update by id
    @PutMapping("/todos/{id}")
    public void updateTodoById(@PathVariable("id") Long id, @RequestParam("todo_title") String title) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        if (todoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "todo with id " + id + " does not exist.");
        }

        Todo todo = todoOptional.get();
        todo.setTitle(title);
        todoRepository.save(todo);
    }






}
