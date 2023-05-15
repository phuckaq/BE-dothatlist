package phuc.bedothatlist.todolist.mapper;

import org.springframework.stereotype.Component;
import phuc.bedothatlist.todolist.dto.TodoListDTO;
import phuc.bedothatlist.todolist.entity.TodoList;

@Component
public class TodoListMapper {

    public TodoListDTO toDto(TodoList todoList) {
        return TodoListDTO.builder()
                .id(todoList.getId())
                .name(todoList.getName())
                .note(todoList.getNote())
                .createdAt(todoList.getCreatedAt())
                .build();
    }

    public TodoList toEntity(TodoListDTO todoListDTO) {
        return TodoList.builder()
                .id(todoListDTO.getId())
                .name(todoListDTO.getName())
                .note(todoListDTO.getNote())
                .createdAt(todoListDTO.getCreatedAt())
                .build();
    }

}
