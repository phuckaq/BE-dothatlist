package phuc.bedothatlist.todolist.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import phuc.bedothatlist.todolist.exception.TodoListNotFoundException;
import phuc.bedothatlist.todolist.exception.model.ErrorResponse;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class TodoListExceptionHandler {

    @ExceptionHandler(TodoListNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTodoListNotFoundException(TodoListNotFoundException ex, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .error(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
