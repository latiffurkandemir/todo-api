package com.todoapp.todo_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @ToString.Exclude
    @Column(name = "password")
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<CategoryEntity> categoryEntityList;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<TaskEntity> taskEntityList;

    public void addTask(TaskEntity task) {
        if (taskEntityList == null) {
            taskEntityList = new ArrayList<>();
        }

        taskEntityList.add(task);
        task.setUser(this);
    }

    public void addCategory(CategoryEntity category) {
        if (categoryEntityList == null) {
            categoryEntityList = new ArrayList<>();
        }

        categoryEntityList.add(category);
        category.setUser(this);
    }
}
