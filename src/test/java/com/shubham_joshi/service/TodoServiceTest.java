package com.shubham_joshi.service;

import com.shubham_joshi.domain.ItemStatus;
import com.shubham_joshi.domain.Todo;
import com.shubham_joshi.entity.TodoEntity;
import com.shubham_joshi.exception.TodoException;
import com.shubham_joshi.repository.TodoRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class TodoServiceTest {
    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @Test
    void shouldSaveTodoEntity() {
        Todo todoItem = Todo.builder()
                .title("item 1")
                .status(ItemStatus.ACTIVE)
                .order(1)
                .build();

        todoService.saveTodoItem(todoItem);

        Mockito.verify(todoRepository, Mockito.times(1)).save(ArgumentMatchers.any(TodoEntity.class));
    }

    @Test
    void shouldReturnListOfTodos() {
        TodoEntity todoEntity = new TodoEntity();
        Mockito.when(todoRepository.findAll()).thenReturn(Collections.singletonList(todoEntity));

        List<TodoEntity> result = todoService.getTodoList();

        Assert.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Mockito.verify(todoRepository, Mockito.times(1)).findAll();
    }

    @Test
    void shouldReturnTodoEntity() {
        TodoEntity todoEntity = new TodoEntity();
        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.of(todoEntity));

        TodoEntity result = todoService.getTodoItem(1L);

        Assert.assertNotNull(result);
        Mockito.verify(todoRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenTodoNotFound() {
        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        TodoException exception = Assert.assertThrows(TodoException.class, () -> todoService.getTodoItem(1L));
        Assertions.assertEquals("Invalid id", exception.getMessage());
        Mockito.verify(todoRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void shouldUpdateTodoEntity() {
        Todo todoItem = Todo.builder()
                .title("item 1 update")
                .status(ItemStatus.COMPLETED)
                .order(1)
                .build();

        TodoEntity todoEntity = new TodoEntity();
        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.of(todoEntity));

        todoService.updateTodoItem(todoItem, 1L);

        Assertions.assertEquals(1, todoEntity.getOrder());
        Assertions.assertEquals("item 1 update", todoEntity.getTitle());
        Assertions.assertEquals(ItemStatus.COMPLETED, todoEntity.getStatus());
        Mockito.verify(todoRepository, Mockito.times(1)).saveAndFlush(todoEntity);
    }

    @Test
    void shouldDeleteTodoEntity() {
        TodoEntity todoEntity = new TodoEntity();
        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.of(todoEntity));

        todoService.deleteTodoItem(1L);

        Mockito.verify(todoRepository, Mockito.times(1)).delete(todoEntity);
    }
}