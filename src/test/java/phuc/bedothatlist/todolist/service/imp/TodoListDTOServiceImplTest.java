package phuc.bedothatlist.todolist.service.imp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import phuc.bedothatlist.todolist.dto.TodoListDTO;
import phuc.bedothatlist.todolist.entity.TodoList;
import phuc.bedothatlist.todolist.exception.TodoListNotFoundException;
import phuc.bedothatlist.todolist.mapper.TodoListMapper;
import phuc.bedothatlist.todolist.repository.TodoListRepository;
import phuc.bedothatlist.todolist.service.TodoListService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TodoListDTOServiceImplTest {

    @Mock
    private TodoListRepository todoListRepository;
    @Mock
    private TodoListMapper todoListMapper;
    @InjectMocks
    private TodoListService todoListService;
    private TodoList todoList1;
    private TodoList todoList2;

    private TodoList todoList3;

    private TodoListDTO todoListDTO1;

    private TodoListDTO todoListDTO2;


    private List<TodoList> list;

    private List<TodoListDTO> listDTOS;

    @BeforeEach
    void setUp() {
        todoListRepository = mock(TodoListRepository.class);
        todoListMapper = mock(TodoListMapper.class);
        todoListService = new TodoListServiceImp(todoListRepository, todoListMapper);
        todoList1 = new TodoList();
        todoList1.setId(1L);
        todoList2 = new TodoList();
        todoList2.setId(2L);
        todoList3 = new TodoList();
        list = new ArrayList<>();
        list.add(todoList1);
        list.add(todoList2);
        todoListDTO1 = new TodoListDTO();
        todoListDTO1.setId(1L);
        todoListDTO2 = new TodoListDTO();
        listDTOS = new ArrayList<>();
        listDTOS.add(todoListDTO1);
        listDTOS.add(todoListDTO2);
    }

    @Test
    void getListsShouldReturnEmptyList() {
        // Mocking behavior
        when(todoListRepository.findAll()).thenReturn(Collections.emptyList());

        // Test the service method
        List<TodoListDTO> todoLists = todoListService.getLists();

        // Assertions
        assertEquals(0, todoLists.size());

        // Verification
        verify(todoListRepository).findAll();
    }

    @Test
    void givenListsGetListsShouldReturnList() {
        when(todoListRepository.findAll()).thenReturn(list);
        when(todoListMapper.mapToDtoList(list)).thenReturn(listDTOS);

        // Act
        List<TodoListDTO> todoLists = todoListService.getLists();

        // Assert
        assertEquals(2, todoLists.size());
        assertEquals(listDTOS, todoLists);

        // Verify
        verify(todoListRepository).findAll();
        verify(todoListMapper).mapToDtoList(list);
    }

    @Test
    void getListShouldThrowException() {
        Long illegalId = 0L;
        when(todoListRepository.findById(illegalId)).thenReturn(Optional.empty());
        assertThrows(TodoListNotFoundException.class, () -> {
            todoListService.getList(illegalId);
        });
    }

    @Test
    void getListShouldReturn() {
        when(todoListRepository.findById(1L)).thenReturn(Optional.ofNullable(todoList1));
        assertEquals(1L, todoList1.getId());
    }

    @Test
    void saveList_Success() {
        when(todoListRepository.save(todoList3)).thenReturn(todoList3);
        when(todoListMapper.mapToEntity(todoListDTO2)).thenReturn(todoList3);
        when(todoListMapper.mapToDto(todoList3)).thenReturn(todoListDTO2);
        TodoListDTO todoList = todoListService.saveList(todoListDTO2);
        assertNotNull(todoList);
        verify(todoListRepository).save(todoList3);
    }

    @Test
    void saveList_Success_Update() {
        when(todoListRepository.save(todoList1)).thenReturn(todoList1);
        when(todoListRepository.findById(todoList1.getId())).thenReturn(Optional.of(todoList1));
        when(todoListMapper.mapToEntity(todoListDTO1)).thenReturn(todoList1);
        when(todoListMapper.mapToDto(todoList1)).thenReturn(todoListDTO1);
        TodoListDTO todoList = todoListService.saveList(todoListDTO1);
        assertNotNull(todoList);
        verify(todoListRepository).findById(todoList1.getId());
        verify(todoListRepository).save(todoList1);
    }

    @Test
    void saveListNull_Failed() {
        doThrow(new IllegalArgumentException("Cannot save null object"))
                .when(todoListRepository)
                .save(null);
    }

    @Test
    void deleteList_Success() {
        when(todoListRepository.save(todoList1)).thenReturn(todoList1);
        doNothing().when(todoListRepository).deleteById(1L);
        todoListRepository.deleteById(1L);
        verify(todoListRepository).deleteById(1L);
    }

    @Test
    void deleteList_Failed() {
        Long id = 1L;
        doThrow(new TodoListNotFoundException("Could not find TodoList with ID: " + id)).when(todoListRepository).deleteById(id);
        assertThrows(TodoListNotFoundException.class, () -> todoListRepository.deleteById(id));
        verify(todoListRepository).deleteById(id);
    }
}