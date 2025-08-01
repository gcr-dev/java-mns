package com.example.todolist.repository;

import com.example.todolist.model.Task;
import com.example.todolist.model.Task.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserId(Long userId);
    List<Task> findByUserIdAndStatus(Long userId, TaskStatus status);
    List<Task> findByStatus(TaskStatus status);
    Long countByUserId(Long userId);
    Long countByUserIdAndStatus(Long userId, TaskStatus status);
    Long countByStatus(TaskStatus status);
}