package br.com.raphael.todolist.mapper;

import br.com.raphael.todolist.domain.dto.TodoRequest;
import br.com.raphael.todolist.domain.dto.TodoResponse;
import br.com.raphael.todolist.domain.model.Todo;

public class TodoMapper {

    public static Todo toTodo(TodoRequest request) {
        Todo todo = new Todo();
        todo.setTitle(request.title());
        todo.setDescription(request.description());
        todo.setConcluded(request.concluded());
        todo.setPriority(request.priority());
        return todo;
    }
    public static TodoResponse toResponse(Todo todo) {
        return new TodoResponse(
                todo.getId(), todo.getTitle(), todo.getDescription(),
                todo.isConcluded(), todo.getCreationDate(), todo.getCompletionDate(),
                todo.getPriority());
    }

}
