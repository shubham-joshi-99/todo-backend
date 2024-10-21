package com.shubham_joshi.service;

import com.shubham_joshi.domain.ToDo;
import com.shubham_joshi.entity.TodoEntity;
import com.shubham_joshi.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepository todoRepository;

    public void saveTodoItem(ToDo toDoItem) {
        TodoEntity entity = new TodoEntity(null, toDoItem.title(), toDoItem.status(), toDoItem.order());

        todoRepository.save(entity);
    }

    public List<TodoEntity> getTodoList() {
        return todoRepository.findAll();
    }

    public TodoEntity getTodoItem(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new RuntimeException("Invalid id"));
    }

    public void updateTodoItem(ToDo toDoItem, long id) {
        TodoEntity entity = getTodoItem(id);
        entity.setOrder(toDoItem.order());
        entity.setTitle(toDoItem.title());
        entity.setStatus(toDoItem.status());
        todoRepository.saveAndFlush(entity);
    }

    public void deleteTodoItem(long id) {
        TodoEntity todoEntity = getTodoItem(id);
        todoRepository.delete(todoEntity);
    }
}
