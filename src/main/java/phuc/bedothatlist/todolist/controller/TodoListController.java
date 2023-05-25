package phuc.bedothatlist.todolist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import phuc.bedothatlist.todolist.dto.TodoListDTO;
import phuc.bedothatlist.todolist.service.TodoListService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/todo-lists")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @GetMapping
    public ResponseEntity<List<TodoListDTO>> getTodoLists() {
        List<TodoListDTO> todoListDTOs = todoListService.getLists();
        return ResponseEntity.ok(todoListDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoListDTO> getTodoList(@PathVariable("id") Long id) {
        TodoListDTO todoListDTO = todoListService.getList(id);
        if (todoListDTO != null) {
            return ResponseEntity.ok(todoListDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<TodoListDTO> createTodoList(@RequestBody TodoListDTO todoListDto) {
        TodoListDTO savedTodoListDTO = todoListService.saveList(todoListDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTodoListDTO.getId()).toUri();
        return ResponseEntity.created(location).body(savedTodoListDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoListDTO> updateTodoList(@PathVariable("id") Long id, @RequestBody TodoListDTO todoListDto) {
        todoListDto.setId(id);
        TodoListDTO updatedTodoListDTO = todoListService.saveList(todoListDto);
        return ResponseEntity.ok(updatedTodoListDTO);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoList(@PathVariable("id") Long id) {
        todoListService.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}
