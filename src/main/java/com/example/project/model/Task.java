package com.example.project.model;

import com.example.project.exception.UnknownStatusException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    private String id;
    private String title;
    private Status status;
    private String detail;
    private Date due;

    public enum Status {
        TODO("待辦"),
        IN_PROGRESS("進行中"),
        DONE("已完成");

        private final String text;

        Status(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public static Status fromText(String text) {
            for (Status s : values()) {
                if (s.text.equals(text)) return s;
            }
            throw new UnknownStatusException("任務未知狀態: " + text);
        }
    }

    public String getFormattedDate() {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy/MM/dd");
        return sdf.format(this.getDue());
    }

    public String getStatusText() {
        return switch (status) {
            case Status.TODO -> "待辦";
            case Status.IN_PROGRESS -> "進行中";
            case Status.DONE -> "已完成";
            default ->  throw new IllegalArgumentException("未知狀態: " + status);
        };
    }
}
