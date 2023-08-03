package br.com.raphael.todolist.service;

import br.com.raphael.todolist.domain.model.Todo;
import br.com.raphael.todolist.exception.ServiceException;
import br.com.raphael.todolist.repository.TodoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    private TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public Todo create(Todo todo) {
        return todoRepository.save(todo);
    }

    public Optional<Todo> listById(Long id) {
        return Optional.ofNullable(todoRepository.findById(id).orElseThrow(() -> new ServiceException("Product not found")));
    }
    public List<Todo> list() {
        Sort sort = Sort.by(Sort.Direction.DESC, "priority");
       return todoRepository.findAll(sort);
    }

    public Todo update(Todo todo) {
        return todoRepository.save(todo);
    }

    public String delete(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo todo = optionalTodo.orElse(null);
        todoRepository.delete(todo);
        return "Deleted successfully";
    }
}
