package com.vasifdev.ToDoAppwithSpringBoot.config;

import com.vasifdev.ToDoAppwithSpringBoot.models.ToDoItem;
import com.vasifdev.ToDoAppwithSpringBoot.repositories.ToDoItemRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

//import java.util.logging.Logger;

@Component
public class ToDoItemDataLoader implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ToDoItemDataLoader.class);

    @Autowired
    ToDoItemRepository toDoItemRepository;

    @Override
    public void run (String... args) throws Exception{
        loadSeedData();
    }

    private void loadSeedData() {
        if (toDoItemRepository.count() == 0) {
            ToDoItem toDoItem1 = new ToDoItem("read the case");
            ToDoItem toDoItem2 = new ToDoItem("prepare for Finance");

            toDoItemRepository.save(toDoItem1);
            toDoItemRepository.save(toDoItem2);

        }

        logger.info("Number of ToDoItems: {}", toDoItemRepository.count());
    }
}
