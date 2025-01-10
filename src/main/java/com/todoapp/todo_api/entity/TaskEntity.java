package com.todoapp.todo_api.entity;

import com.todoapp.todo_api.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "task")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskEntity extends BaseEntity{

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @Column(name = "is_deleted")
    @Builder.Default
    private boolean isDeleted = false;

    @PrePersist
    public void setDefaultStatus() {
        if (this.status == null) {
            this.status = TaskStatus.IN_PROGRESS;
        }
    }
}
