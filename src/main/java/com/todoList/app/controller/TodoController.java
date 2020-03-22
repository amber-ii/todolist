package com.todoList.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.todoList.app.entity.Todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private List<Todo> todoList = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodo() {
        return ResponseEntity.ok(this.todoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable("id") Integer id) {
        final Optional<Todo> todoOpt = this.todoList.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (!todoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todoOpt.get());
    }

    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody @Valid Todo todo) {
        if (this.todoList.size() == 0) {
            todo.setId(0);
        } else {
            todo.setId(this.todoList.get(this.todoList.size() - 1).getId() + 1);
        }
        this.todoList.add(todo);
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(todo.getId()).toUri())
                .body(todo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") Integer id, @RequestBody @Valid Todo todo) {
        final Optional<Todo> todoOpt = this.todoList.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (!todoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        todo.setId(todoOpt.get().getId());
        this.todoList.set(this.todoList.indexOf(todoOpt.get()), todo);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable("id") Integer id) {
        final Optional<Todo> todoOpt = this.todoList.stream().filter(t -> t.getId().equals(id)).findFirst();
        if (!todoOpt.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        this.todoList.remove(todoOpt.get());
        return ResponseEntity.noContent().build();
    }
}