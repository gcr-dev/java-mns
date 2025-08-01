package com.example.todolist.controller;

import com.example.todolist.model.Task;
import com.example.todolist.model.User;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public String adminDashboard(Model model) {
        // Statistiques générales
        model.addAttribute("userCount", userRepository.count());
        model.addAttribute("taskCount", taskRepository.count());
        model.addAttribute("inProgressCount", 
            taskRepository.countByStatus(Task.TaskStatus.IN_PROGRESS));
        model.addAttribute("completedCount", 
            taskRepository.countByStatus(Task.TaskStatus.COMPLETED));
        
        // Statistiques par utilisateur
        List<UserStatistics> userStats = new ArrayList<>();
        List<User> users = userRepository.findAll();
        
        for (User user : users) {
            UserStatistics stat = new UserStatistics();
            stat.setUserName(user.getName());
            stat.setTotalTasks(taskRepository.countByUserId(user.getId()));
            stat.setCompletedTasks(
                taskRepository.countByUserIdAndStatus(user.getId(), Task.TaskStatus.COMPLETED));
            stat.setInProgressTasks(
                taskRepository.countByUserIdAndStatus(user.getId(), Task.TaskStatus.IN_PROGRESS));
            stat.setTodoTasks(
                taskRepository.countByUserIdAndStatus(user.getId(), Task.TaskStatus.TODO));
            userStats.add(stat);
        }
        
        model.addAttribute("userStats", userStats);
        
        return "admin/dashboard";
    }
    
    public static class UserStatistics {
        private String userName;
        private Long totalTasks;
        private Long completedTasks;
        private Long inProgressTasks;
        private Long todoTasks;
        
        // Getters and setters
        public String getUserName() { return userName; }
        public void setUserName(String userName) { this.userName = userName; }
        public Long getTotalTasks() { return totalTasks; }
        public void setTotalTasks(Long totalTasks) { this.totalTasks = totalTasks; }
        public Long getCompletedTasks() { return completedTasks; }
        public void setCompletedTasks(Long completedTasks) { this.completedTasks = completedTasks; }
        public Long getInProgressTasks() { return inProgressTasks; }
        public void setInProgressTasks(Long inProgressTasks) { this.inProgressTasks = inProgressTasks; }
        public Long getTodoTasks() { return todoTasks; }
        public void setTodoTasks(Long todoTasks) { this.todoTasks = todoTasks; }
    }
}