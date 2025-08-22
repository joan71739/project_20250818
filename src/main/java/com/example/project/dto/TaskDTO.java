package com.example.project.dto;

import com.example.project.model.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private String id;
    private String title;
    private String status;
    private String detail;
    private String due;

    public TaskDTO(Task task) {
        this.id = task.getId();
        this.title = task.getTitle();
        this.status = task.getStatusText();
        this.detail = task.getDetail();
        this.due = task.getFormattedDate();
    }




}
