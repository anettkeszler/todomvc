package com.example.todomvc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Component
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Status status;
    private static int _idCounter = 0;

    public boolean isCompleted() {
        return this.status == Status.COMPLETE;
    }

    public Todo create(String title) {
        _idCounter++;
        return new Todo(id, title, Status.ACTIVE);
    }

}
