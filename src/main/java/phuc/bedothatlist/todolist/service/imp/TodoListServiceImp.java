package phuc.bedothatlist.todolist.service.imp;


import org.springframework.stereotype.Service;
import phuc.bedothatlist.todolist.dto.TodoListDTO;
import phuc.bedothatlist.todolist.entity.TodoList;
import phuc.bedothatlist.todolist.exception.TodoListNotFoundException;
import phuc.bedothatlist.todolist.mapper.TodoListMapper;
import phuc.bedothatlist.todolist.repository.TodoListRepository;
import phuc.bedothatlist.todolist.service.TodoListService;

import java.util.List;
import java.util.Optional;

@Service
public class TodoListServiceImp implements TodoListService {

    private final TodoListMapper todoListMapper;
    private TodoListRepository todoListRepository;


    public TodoListServiceImp(TodoListRepository todoListRepository, TodoListMapper todoListMapper) {
        this.todoListRepository = todoListRepository;
        this.todoListMapper = todoListMapper;
    }

    @Override
    public List<TodoListDTO> getLists() {
        List<TodoList> todoLists = todoListRepository.findAll();
        return todoListMapper.mapToDtoList(todoLists);
    }

    @Override
    public TodoListDTO getList(Long id) {
        Optional<TodoList> todoList = todoListRepository.findById(id);
        if (todoList.isPresent()) {
            return todoListMapper.mapToDto(todoList.get());
        } else {
            throw new TodoListNotFoundException("Could not find TodoList with ID: " + id);
        }
    }

    @Override
    public TodoListDTO saveList(TodoListDTO todoListDTO) {
        TodoList todoList = todoListMapper.mapToEntity(todoListDTO);
        TodoList updatedTodoListDTO = null;
        if (todoList.getId() != null) {
            Optional<TodoList> todoListInDb = todoListRepository.findById(todoList.getId());
            if (todoListInDb.isPresent()) {
                todoListInDb.get().setName(todoListDTO.getName());
                todoListInDb.get().setNote(todoListDTO.getNote());
                updatedTodoListDTO = todoListRepository.save(todoListInDb.get());
            }
        } else {
            updatedTodoListDTO = todoListRepository.save(todoList);
        }
        return todoListMapper.mapToDto(updatedTodoListDTO);
    }

    @Override
    public void deleteList(Long id) {
        try {
            todoListRepository.deleteById(id);
        } catch (TodoListNotFoundException ex) {
            throw new TodoListNotFoundException("Could not find TodoList with ID: " + id);
        }
    }

}
