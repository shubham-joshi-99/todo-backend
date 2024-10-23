package com.shubham_joshi.service;

import com.shubham_joshi.domain.Todo;
import com.shubham_joshi.entity.TodoEntity;
import com.shubham_joshi.exception.TodoException;
import com.shubham_joshi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public void saveTodoItem(Todo todoItem) {
        TodoEntity entity = TodoEntity.builder()
                .order(todoItem.order())
                .status(todoItem.status())
                .title(todoItem.title())
                .build();

        todoRepository.save(entity);
    }

    public List<TodoEntity> getTodoList() {
        return todoRepository.findAll();
    }

    public TodoEntity getTodoItem(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new TodoException("Invalid id"));
    }

    public void updateTodoItem(Todo todoItem, long id) {
        TodoEntity entity = getTodoItem(id);

        entity.setOrder(todoItem.order());
        entity.setTitle(todoItem.title());
        entity.setStatus(todoItem.status());

        todoRepository.saveAndFlush(entity);
    }

    public void deleteTodoItem(long id) {
        TodoEntity todoEntity = getTodoItem(id);
        todoRepository.delete(todoEntity);
    }
}
