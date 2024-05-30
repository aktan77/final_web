package com.vasifdev.ToDoAppwithSpringBoot.controllers;

import com.vasifdev.ToDoAppwithSpringBoot.models.ToDoItem;
import com.vasifdev.ToDoAppwithSpringBoot.repositories.ToDoItemRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.BindException;
import java.time.Instant;
import java.time.ZoneId;

//import java.util.logging.Logger;

@Controller
public class ToDoItemController {
    private final Logger logger = LoggerFactory.getLogger(ToDoItemController.class);

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    @GetMapping("/")
    public ModelAndView index() {
        logger.debug("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", toDoItemRepository.findAll());
        modelAndView.addObject("today", Instant.now().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfWeek());
        return modelAndView;
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid ToDoItem toDoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-doto-item";
        }
        toDoItem.setCreatedDate(Instant.now());
        toDoItem.setModifiedDate(Instant.now());
        toDoItemRepository.save(toDoItem);
        return "redirect:/";
    }

    @PostMapping("/todo/{id}")
    public String updateToDoItem(@PathVariable("id") long id, @Valid ToDoItem toDoItem, BindingResult result, Model model) {
        if(result.hasErrors()) {
            toDoItem.setId(id);
            return "update-todo-item";
        }
        toDoItem.setModifiedDate(Instant.now());
        toDoItemRepository.save(toDoItem);
        return "redirect:/";
    }
}
