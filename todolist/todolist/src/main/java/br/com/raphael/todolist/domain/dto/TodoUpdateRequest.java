package br.com.raphael.todolist.domain.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TodoUpdateRequest(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @Min(1)
        int priority,
        boolean concluded
) {
}
