package phuc.bedothatlist.todolist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class TodoListDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String name;

    @JsonProperty("note")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String note;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;
}
