package com.shubham_joshi.repository;

import com.shubham_joshi.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
