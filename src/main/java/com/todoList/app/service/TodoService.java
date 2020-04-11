package com.todoList.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.todoList.app.entity.Todo;
import com.todoList.app.repository.TodoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getAllTodo() {
        return this.todoRepository.findAll();
    }

    public Optional<Todo> getTodoById(String id) {
        return this.todoRepository.findById(id);
    }

    public void addTodo(Todo todo) {
        this.todoRepository.insert(todo);
    }

    public void updateTodoById(String id, Todo todo) {
        todo.setId(id);
        todo.setUpdateAt(new Date());
        this.todoRepository.save(todo);
    }

    public void deleteTodoById(String id) {
        this.todoRepository.deleteById(id);
    }
}