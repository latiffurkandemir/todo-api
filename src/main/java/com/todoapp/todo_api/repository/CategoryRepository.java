package com.todoapp.todo_api.repository;

import com.todoapp.todo_api.entity.CategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Query("SELECT c FROM CategoryEntity c WHERE LOWER(c.name) = LOWER(:name)")
    Optional<CategoryEntity> findByNameIgnoreCase(@Param("name") String name);


}
