package com.shubham_joshi.controller;

import com.shubham_joshi.domain.Todo;
import com.shubham_joshi.entity.TodoEntity;
import com.shubham_joshi.exception.TodoException;
import com.shubham_joshi.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<TodoEntity>> getTodoList() {
        return ResponseEntity.ok(todoService.getTodoList());
    }

    @PostMapping
    public ResponseEntity<String> saveTodoItem(@RequestBody Todo request) {
        todoService.saveTodoItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Saved successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTodoItem(@PathVariable int id, @RequestBody Todo todo) {
        todoService.updateTodoItem(todo, id);
        return ResponseEntity.ok("Updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoItem(@PathVariable int id) {
        todoService.deleteTodoItem(id);
        return ResponseEntity.ok("Deleted successfully");
    }

    @ExceptionHandler(TodoException.class)
    public ResponseEntity<String> handleTodoException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
