package com.vasifdev.ToDoAppwithSpringBoot.repositories;

import com.vasifdev.ToDoAppwithSpringBoot.models.ToDoItem;
import org.springframework.data.repository.CrudRepository;

public interface ToDoItemRepository extends CrudRepository<ToDoItem, Long> {
}
