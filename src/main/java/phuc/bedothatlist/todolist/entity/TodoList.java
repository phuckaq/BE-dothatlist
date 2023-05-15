package phuc.bedothatlist.todolist.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Entity
@Table(name = "todo_list")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String note;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        TodoList todoList = (TodoList) o;
        return getId() != null && Objects.equals(getId(), todoList.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
