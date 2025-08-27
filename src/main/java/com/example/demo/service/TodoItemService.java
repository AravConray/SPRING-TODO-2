package com.example.demo.service;
import com.example.demo.model.TodoItem;
import com.example.demo.repository.TodoItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;

    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public List<TodoItem> findAll() {
        return todoItemRepository.findAll();
    }

    public Optional<TodoItem> findById(Long id) {
        return todoItemRepository.findById(id);
    }

    public TodoItem create(TodoItem item) {
        return todoItemRepository.save(item);
    }

    public TodoItem update(Long id, TodoItem item) {
        return todoItemRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(item.getTitle());
                    existing.setDescription(item.getDescription());
                    existing.setCompleted(item.isCompleted());
                    return todoItemRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("TodoItem not found with id " + id));
    }

    public void delete(Long id) {
        if (!todoItemRepository.existsById(id)) {
            throw new RuntimeException("TodoItem not found with id " + id);
        }
        todoItemRepository.deleteById(id);
    }
}
