package phuc.bedothatlist.todolist.service;


import phuc.bedothatlist.todolist.dto.TodoListDTO;

import java.util.List;

public interface TodoListService {
    List<TodoListDTO> getLists();

    TodoListDTO getList(Long id);

    TodoListDTO saveList(TodoListDTO todoList);

    void deleteList(Long id);
}
