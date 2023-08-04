package br.com.raphael.todolist.controller;

import br.com.raphael.todolist.domain.dto.TodoCreateRequest;
import br.com.raphael.todolist.domain.dto.TodoResponse;
import br.com.raphael.todolist.domain.dto.TodoUpdateRequest;
import br.com.raphael.todolist.domain.model.Todo;
import br.com.raphael.todolist.mapper.TodoMapper;
import br.com.raphael.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/todos", produces = {"application/json"})
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @PostMapping
    public ResponseEntity<List<TodoResponse>> create(@RequestBody @Valid TodoCreateRequest todoCreateRequest) {
        Todo todo = TodoMapper.toTodo(todoCreateRequest);
        List<Todo> updatedTodoList = todoService.create(todo);
        List<TodoResponse> todoResponses = TodoMapper.toResponseList(updatedTodoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(todoResponses);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(todoService.list());
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<List<TodoResponse>> updateEdit(@PathVariable(value = "id") Long id,
                                               @RequestBody TodoUpdateRequest todoUpdate) {
        Todo todo;
        todo = todoService.findById(id);
        TodoMapper.todoUpdateProperties(todoUpdate, todo);
        todoService.update(todo);
        List<Todo> todoList = todoService.list();
        List<TodoResponse> todoResponseList = TodoMapper.toResponseList(todoList);
        return ResponseEntity.status(HttpStatus.OK).body(todoResponseList);
    }

    @PutMapping("/concluded/{id}")
    public ResponseEntity<List<TodoResponse>> Finish(@PathVariable(value = "id") Long id) {
        Todo todo;
        todo = todoService.findById(id);
        todo.setConcluded(true);
        todo.setCompletionDate(LocalDateTime.now());
        todoService.update(todo);
        List<Todo> todoList = todoService.list();
        List<TodoResponse> todoResponseList = TodoMapper.toResponseList(todoList);
        return ResponseEntity.status(HttpStatus.OK).body(todoResponseList);
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<List<Todo>> delete(@PathVariable(value = "id") Long id) {
        List<Todo> todoList = todoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(todoList);
    }
}
