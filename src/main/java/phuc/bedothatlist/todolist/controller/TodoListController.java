package phuc.bedothatlist.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import phuc.bedothatlist.todolist.dto.TodoListDTO;
import phuc.bedothatlist.todolist.entity.TodoList;
import phuc.bedothatlist.todolist.mapper.TodoListMapper;
import phuc.bedothatlist.todolist.service.TodoListService;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/todolists")
public class TodoListController {

    private final TodoListService todoListService;
    private final TodoListMapper todoListMapper;

    public TodoListController(TodoListService todoListService, TodoListMapper todoListMapper) {
        this.todoListService = todoListService;
        this.todoListMapper = todoListMapper;
    }

    @GetMapping
    public List<TodoListDTO> getTodoLists() {

        List<TodoList> todoLists =  todoListService.getLists();
        List<TodoListDTO> todoListDTOS = new ArrayList<>();
        for(TodoList todoList : todoLists){
            todoListDTOS.add( todoListMapper.toDto(todoList));
        }
        return todoListDTOS;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoList> getTodoList(@PathVariable Integer id) {
        TodoList todoList = todoListService.getList(id);
        if (todoList != null) {
            return ResponseEntity.ok(todoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TodoList> createTodoList(@RequestBody TodoList todoList) {
        TodoList savedTodoList = todoListService.saveList(todoList);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTodoList.getId()).toUri();
        return ResponseEntity.created(location).body(savedTodoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoList> updateTodoList(@PathVariable Integer id, @RequestBody TodoList todoList) {
        TodoList existingTodoList = todoListService.getList(id);
        if (existingTodoList != null) {
            todoList.setId(existingTodoList.getId());
            TodoList updatedTodoList = todoListService.saveList(todoList);
            return ResponseEntity.ok(updatedTodoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable Integer id) {
        todoListService.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}
