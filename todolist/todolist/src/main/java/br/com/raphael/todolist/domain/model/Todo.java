package br.com.raphael.todolist.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private boolean concluded;
    private LocalDateTime creationDate;
    private LocalDateTime completionDate;
    private int priority;
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    public Todo(String title, String description, boolean concluded,
                LocalDateTime creationDate, LocalDateTime completionDate, int priority) {
        this.title = title;
        this.description = description;
        this.concluded = concluded;
        this.creationDate = creationDate;
        this.completionDate = completionDate;
        this.priority = priority;
    }

    public Todo() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConcluded() {
        return concluded;
    }

    public void setConcluded(boolean concluded) {
        this.concluded = concluded;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    @PrePersist
    public void prePersist() {
        if (creationDate == null) {
            creationDate = LocalDateTime.now();
        }
    }

    @PreUpdate
    public void preUpdate() {
        if (concluded) {
            completionDate = LocalDateTime.now();
        }
    }
    public String getFormattedCreationDate() {
        return creationDate.format(fmt);
    }

    public String getFormattedCompletionDate() {
        return completionDate != null ? completionDate.format(fmt) : null;
    }
}
