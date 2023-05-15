package phuc.bedothatlist.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phuc.bedothatlist.todolist.entity.TodoList;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}
