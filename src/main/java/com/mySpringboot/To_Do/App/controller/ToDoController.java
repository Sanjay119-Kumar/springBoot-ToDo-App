package com.mySpringboot.To_Do.App.controller;

import com.mySpringboot.To_Do.App.dao.ToDoEntity;
import com.mySpringboot.To_Do.App.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin  // Enable CORS for React frontend
@RequestMapping("/api/todo")

@Validated
public class ToDoController {

    @Autowired
    ToDoService toDoService;

    // GET Api
    @GetMapping
    public List<ToDoEntity> getAllToDo() {
        return toDoService.getAllTodos();
    }

    //GET Api By Id
    @GetMapping("/{id}")
    public ResponseEntity<ToDoEntity> getToDoById(@PathVariable Long id) {
        Optional<ToDoEntity> toDo = toDoService.getToDoById(id);
        if (toDo.isPresent()){
            return ResponseEntity.ok(toDo.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    //POST
    @PostMapping("/add")
    public ResponseEntity<ToDoEntity> addNewTodo(@Valid @RequestBody ToDoEntity toDo) {
        System.out.println("Received ToDo: " + toDo);
        ToDoEntity saveToDo = toDoService.createNewToDo(toDo);
        return ResponseEntity.ok(saveToDo);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<ToDoEntity> updateToDo(@PathVariable Long id, @Valid @RequestBody ToDoEntity toDoDetails) {
        ToDoEntity toDoUpdate = toDoService.updateToDo(id, toDoDetails);
        return ResponseEntity.ok(toDoUpdate);
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDo(@PathVariable Long id) {
        toDoService.deleteToDo(id);
        return ResponseEntity.noContent().build();

    }


}
