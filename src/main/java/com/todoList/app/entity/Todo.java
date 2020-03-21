package com.todoList.app.entity;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class Todo {
    @NotBlank
    private String name;
}