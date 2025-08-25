package com.example.project.controller;

import com.example.project.dto.TaskDTO;
import com.example.project.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController
public class TaskController {

    @Autowired
    public TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskDTO> getTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping("/tasks")
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
        System.out.println(taskDTO);
        TaskDTO newOne = taskService.createTask(taskDTO);
        if(!Objects.isNull(newOne)){
            return ResponseEntity.ok(newOne);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable String id, @RequestBody TaskDTO taskDTO) {
        TaskDTO updated = taskService.updateTask(id, taskDTO);
        if (!Objects.isNull(updated)) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<Void> deleteTask(@RequestParam String id) {
        boolean deleted = taskService.deleteTaskById(id);
        if(deleted) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
