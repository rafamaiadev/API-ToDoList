package br.com.raphael.todolist.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TodoRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        boolean concluded,
        @Min(1)
        int priority
) {
}
