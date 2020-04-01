package com.todolist.app.entity;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "todos")
public class Todo {
    @Id
    private String id;

    @NotBlank
    private String name;
    @NotNull
    private Boolean done;

    @Indexed
    private Date updateAt;
}