package com.example.todolist.controller;

import com.example.todolist.model.Task;
import com.example.todolist.model.User;
import com.example.todolist.repository.TaskRepository;
import com.example.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks/list";
    }

    @GetMapping("/{id}")
    public String getTaskDetail(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        model.addAttribute("task", task);
        return "tasks/detail";
    }
    
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userRepository.findAll());
        return "tasks/form";
    }
    
    @PostMapping("/create")
    public String createTask(@Valid @ModelAttribute Task task, 
                            BindingResult result,
                            @RequestParam Long userId,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            return "tasks/form";
        }
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        taskRepository.save(task);
        
        redirectAttributes.addFlashAttribute("successMessage", "Tâche créée avec succès !");
        return "redirect:/tasks";
    }
    
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        model.addAttribute("task", task);
        model.addAttribute("users", userRepository.findAll());
        return "tasks/form";
    }
    
    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id,
                            @Valid @ModelAttribute Task task,
                            BindingResult result,
                            @RequestParam Long userId,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("users", userRepository.findAll());
            return "tasks/form";
        }
        
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setPriority(task.getPriority());
        existingTask.setStatus(task.getStatus());
        existingTask.setUser(user);
        
        taskRepository.save(existingTask);
        
        redirectAttributes.addFlashAttribute("successMessage", "Tâche mise à jour avec succès !");
        return "redirect:/tasks";
    }
    
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        taskRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Tâche supprimée avec succès !");
        return "redirect:/tasks";
    }
}