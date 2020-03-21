package com.todoList.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.todoList.app.entity.Todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private List<Todo> todoList = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Todo>> getTodo() {
        return ResponseEntity.ok(this.todoList);
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody @Valid Todo todo) {
        this.todoList.add(todo);
        return ResponseEntity.ok(todo);
    }
}