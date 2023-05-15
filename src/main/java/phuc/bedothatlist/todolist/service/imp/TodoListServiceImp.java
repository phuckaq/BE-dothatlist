package phuc.bedothatlist.todolist.service.imp;


import org.springframework.stereotype.Service;
import phuc.bedothatlist.todolist.entity.TodoList;
import phuc.bedothatlist.todolist.repository.TodoListRepository;
import phuc.bedothatlist.todolist.service.TodoListService;

import java.util.List;

@Service
public class TodoListServiceImp implements TodoListService {

    private TodoListRepository todoListRepository;

    public TodoListServiceImp(TodoListRepository todoListRepository) {
        this.todoListRepository = todoListRepository;
    }

    @Override
    public List<TodoList> getLists() {
        return todoListRepository.findAll();
    }

    @Override
    public TodoList getList(Integer id) {
        return null;
    }

    @Override
    public TodoList saveList(TodoList todoList) {
        return null;
    }

    @Override
    public void deleteList(Integer id) {

    }

}
