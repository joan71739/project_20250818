package com.example.project.service;

import com.example.project.dto.TaskDTO;
import com.example.project.model.People;
import com.example.project.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects; // 修正：匯入 Objects

@Service
public class TaskService {
    static List<Task> list =new ArrayList<Task>();

    static {
        list.add(new Task("1", "學習React",Task.Status.IN_PROGRESS, "學習React的基礎知識", new Date("2025/08/21")));
        list.add(new Task("2", "學習Spring Boot",Task.Status.TODO, "學習Spring Boot的基礎知識", new Date()));
    }

    public List<TaskDTO> getAllTasks() {
        return list.stream()
                .map(TaskDTO::new)
                .toList();
    }

    public TaskDTO createTask(TaskDTO taskDTO) {

        int maxId = list.stream().mapToInt(task -> Integer.parseInt(task.getId()))
                .max()
                .orElse(0);

        String newId = String.valueOf(maxId + 1);

        Date dueDate = null;
        try {
            dueDate = new SimpleDateFormat("yyyy/MM/dd").parse(taskDTO.getDue());
        } catch (ParseException e) {
            dueDate = new Date(); // 或自訂預設日期
        }

        Task newTask = new Task(
                newId,
                taskDTO.getTitle(),
                Task.Status.fromText(taskDTO.getStatus()),
                taskDTO.getDetail(),
                dueDate
        );
        list.add(newTask);
        return new TaskDTO(newTask);
    }

    public boolean deleteTaskById(String id) {
        return list.removeIf(task -> task.getId().equals(id));
    }

    public TaskDTO updateTask(String id, TaskDTO taskDTO) {
        Task target = list.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElse(null);
        if (!Objects.isNull(target)) {
            target.setTitle(taskDTO.getTitle());
            target.setStatus(Task.Status.fromText(taskDTO.getStatus()));
            target.setDetail(taskDTO.getDetail());
            try {
                target.setDue(new SimpleDateFormat("yyyy/MM/dd").parse(taskDTO.getDue()));
            } catch (ParseException e) {
                target.setDue(new Date());
            }
            return new TaskDTO(target);
        }
        return null;
    }
}
