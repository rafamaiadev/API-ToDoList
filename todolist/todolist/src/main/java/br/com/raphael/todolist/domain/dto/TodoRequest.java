package br.com.raphael.todolist.domain.dto;

import java.time.LocalDateTime;

public record TodoRequest(
        String title,
        String description,
        boolean concluded,
        int priority
) {
}
