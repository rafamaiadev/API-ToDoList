package br.com.raphael.todolist.service;

import br.com.raphael.todolist.domain.model.Todo;
import br.com.raphael.todolist.mapper.TodoMapper;
import br.com.raphael.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> create() {

    }

    public List<Todo> list() {

    }

    public List<Todo> update() {

    }

    public List<Todo> delete() {

    }
}
