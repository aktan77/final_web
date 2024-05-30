package com.vasifdev.ToDoAppwithSpringBoot.controllers;

import com.vasifdev.ToDoAppwithSpringBoot.models.ToDoItem;
import com.vasifdev.ToDoAppwithSpringBoot.repositories.ToDoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ToDoFormController {

    private final Logger logger = LoggerFactory.getLogger(ToDoFormController.class);

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping("/create-todo")
    public String showCreateForm(ToDoItem toDoItem) {
        return "add-todo-item";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        ToDoItem toDoItem = toDoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todoitem id:" + id + "Item not found!"));

        model.addAttribute("todo", toDoItem);

        return "update-todo-item";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") long id, Model model) {
        ToDoItem toDoItem = toDoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todoitem id:" + id + "Item not found!"));

        toDoItemRepository.delete(toDoItem);
        return "redirect:/";
    }

}
