package com.example.todolist.controller.api;

import com.example.todolist.model.Task;
import com.example.todolist.model.User;
import com.example.todolist.dto.TaskDTO;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(task -> ResponseEntity.ok(new TaskDTO(task)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<TaskDTO> getTasksByUserId(@PathVariable Long userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(TaskDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return userRepository.findById(taskRequest.getUserId())
                .map(user -> {
                    Task task = new Task(taskRequest.getTitle(), taskRequest.getDescription(), user);
                    task.setPriority(taskRequest.getPriority());
                    task.setStatus(taskRequest.getStatus());
                    Task savedTask = taskRepository.save(task);
                    return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
                })
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(taskRequest.getTitle());
                    task.setDescription(taskRequest.getDescription());
                    task.setPriority(taskRequest.getPriority());
                    task.setStatus(taskRequest.getStatus());
                    
                    if (taskRequest.getUserId() != null) {
                        userRepository.findById(taskRequest.getUserId()).ifPresent(task::setUser);
                    }
                    
                    Task updatedTask = taskRepository.save(task);
                    return ResponseEntity.ok(updatedTask);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    static class TaskRequest {
        @NotBlank
        private String title;
        private String description;
        private Long userId;
        private Task.TaskPriority priority = Task.TaskPriority.MEDIUM;
        private Task.TaskStatus status = Task.TaskStatus.TODO;

        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
        public Task.TaskPriority getPriority() { return priority; }
        public void setPriority(Task.TaskPriority priority) { this.priority = priority; }
        public Task.TaskStatus getStatus() { return status; }
        public void setStatus(Task.TaskStatus status) { this.status = status; }
    }
}