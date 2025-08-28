package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.TodoItem;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
    // Add custom query methods here if needed
}
