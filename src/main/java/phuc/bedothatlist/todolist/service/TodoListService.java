package phuc.bedothatlist.todolist.service;

import phuc.bedothatlist.todolist.entity.TodoList;

import java.util.List;

public interface TodoListService {
    List<TodoList> getLists();
    TodoList getList(Integer id);

    TodoList saveList(TodoList todoList);

    void deleteList(Integer id);
}
