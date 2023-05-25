package phuc.bedothatlist.todolist.mapper;

import org.springframework.stereotype.Component;
import phuc.bedothatlist.todolist.dto.TodoListDTO;
import phuc.bedothatlist.todolist.entity.TodoList;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoListMapper {

    public TodoListDTO mapToDto(TodoList todoList) {
        return TodoListDTO.builder()
                .id(todoList.getId())
                .name(todoList.getName())
                .note(todoList.getNote())
                .createdAt(todoList.getCreatedAt())
                .build();
    }

    public TodoList mapToEntity(TodoListDTO todoListDTO) {
        return TodoList.builder()
                .id(todoListDTO.getId())
                .name(todoListDTO.getName())
                .note(todoListDTO.getNote())
                .createdAt(todoListDTO.getCreatedAt())
                .build();
    }

    public List<TodoListDTO> mapToDtoList(List<TodoList> todoLists) {
        return todoLists.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
}
