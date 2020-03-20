package com.todoList.app.controller;

import java.util.ArrayList;
import java.util.List;

import com.todoList.app.entity.Todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    private List<Todo> todoList = new ArrayList<>();

    @GetMapping(value = "/todos")
    public ResponseEntity<List<Todo>> getTodo() {
        this.todoList.add(new Todo("lunch"));
        return ResponseEntity.ok().body(this.todoList);
    }
}