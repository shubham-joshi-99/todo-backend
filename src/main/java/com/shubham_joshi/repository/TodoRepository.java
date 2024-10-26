package com.shubham_joshi.repository;

import com.shubham_joshi.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
    List<TodoEntity> findAllByOrderByOrderAsc();
}
