package com.shubham_joshi.controller;

import com.shubham_joshi.domain.ItemStatus;
import com.shubham_joshi.domain.Todo;
import com.shubham_joshi.entity.TodoEntity;
import com.shubham_joshi.exception.TodoException;
import com.shubham_joshi.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TodoControllerTest {
    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;

    @Test
    void shouldReturnListOfTodos() {
        TodoEntity todoEntity = new TodoEntity();
        Mockito.when(todoService.getTodoList()).thenReturn(Collections.singletonList(todoEntity));

        ResponseEntity<List<TodoEntity>> response = todoController.getTodoList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        Mockito.verify(todoService, Mockito.times(1)).getTodoList();
    }

    @Test
    void saveTodoItem_shouldReturnSuccessMessage() {
        Todo todoRequest = Todo.builder()
                .title("item 1")
                .status(ItemStatus.ACTIVE)
                .order(1)
                .build();

        ResponseEntity<String> response = todoController.saveTodoItem(todoRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Saved successfully", response.getBody());
        Mockito.verify(todoService, Mockito.times(1)).saveTodoItem(todoRequest);
    }

    @Test
    void updateTodoItem_shouldReturnSuccessMessage() {
        Todo todoRequest = Todo.builder()
                .title("item 1 update")
                .status(ItemStatus.COMPLETED)
                .order(1)
                .build();
        int todoId = 1;

        ResponseEntity<String> response = todoController.updateTodoItem(todoId, todoRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated successfully", response.getBody());
        Mockito.verify(todoService, Mockito.times(1)).updateTodoItem(todoRequest, todoId);
    }

    @Test
    void shouldReturnSuccessMessage() {
        int todoId = 1;

        ResponseEntity<String> response = todoController.deleteTodoItem(todoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted successfully", response.getBody());
        Mockito.verify(todoService, Mockito.times(1)).deleteTodoItem(todoId);
    }

    @Test
    void shouldReturnBadRequest() {
        TodoException ex = new TodoException("Todo item not found");

        ResponseEntity<String> response = todoController.handleTodoException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Todo item not found", response.getBody());
    }

    @Test
    void shouldReturnInternalServerError() {
        RuntimeException ex = new RuntimeException("Internal server error");

        ResponseEntity<String> response = todoController.handleRuntimeException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal server error", response.getBody());
    }
}