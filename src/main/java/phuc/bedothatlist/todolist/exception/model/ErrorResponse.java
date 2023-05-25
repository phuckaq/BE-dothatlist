package phuc.bedothatlist.todolist.exception.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private int status;
    private String error;
    private String path;

    // Constructors, getters, and setters

}
