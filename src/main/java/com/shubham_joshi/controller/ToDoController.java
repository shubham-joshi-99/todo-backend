package com.shubham_joshi.controller;

import com.shubham_joshi.domain.ToDo;
import com.shubham_joshi.entity.TodoEntity;
import com.shubham_joshi.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin(
        methods = {POST, GET, OPTIONS, DELETE, PATCH},
        maxAge = 3600,
        allowedHeaders = {"x-requested-with", "origin", "content-type", "accept"},
        origins = "*"
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class ToDoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoEntity>> getTodoList() {
        return ResponseEntity.ok(todoService.getTodoList());
    }

    @PostMapping
    public ResponseEntity<String> saveTodoItem(@RequestBody ToDo request) {
        todoService.saveTodoItem(request);
        return ResponseEntity.ok("Saved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodoItem(@PathVariable int id, @RequestBody ToDo todo) {
        todoService.updateTodoItem(todo, id);
        return ResponseEntity.ok("Updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoItem(@PathVariable int id) {
        todoService.deleteTodoItem(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
