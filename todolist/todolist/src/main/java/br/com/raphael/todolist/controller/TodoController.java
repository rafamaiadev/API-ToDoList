package br.com.raphael.todolist.controller;

import br.com.raphael.todolist.domain.dto.TodoRequest;
import br.com.raphael.todolist.domain.dto.TodoResponse;
import br.com.raphael.todolist.domain.model.Todo;
import br.com.raphael.todolist.mapper.TodoMapper;
import br.com.raphael.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody @Valid TodoRequest todoRequest) {
        Todo todo = TodoMapper.toTodo(todoRequest);
        todoService.create(todo);
        return ResponseEntity.status(HttpStatus.CREATED).body(TodoMapper.toResponse(todo));
    }
    @GetMapping
    public ResponseEntity<List<Todo>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.list());
    }
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable(value = "id") Long id,
                                               @RequestBody TodoRequest todoRequest) {
        Optional<Todo> optionalTodo = todoService.listById(id);
        Todo todo = optionalTodo.get();
        todo = TodoMapper.toTodo(todoRequest);
        todoService.update(todo);
        return ResponseEntity.status(HttpStatus.OK).body(TodoMapper.toResponse(todo));
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        return todoService.delete(id);
    }
}
