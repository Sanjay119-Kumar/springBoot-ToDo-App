package com.mySpringboot.To_Do.App.service;

import com.mySpringboot.To_Do.App.dao.ToDoEntity;
import com.mySpringboot.To_Do.App.dao.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    // Method to save a ToDo entity
    public ToDoEntity save(ToDoEntity toDoEntity) {
        return toDoRepository.save(toDoEntity);
    }

    // Fetch all todo
    public List<ToDoEntity> getAllTodos(){
       return toDoRepository.findAll();
    }

     // find by id
    public Optional<ToDoEntity> getToDoById(Long id){
        return toDoRepository.findById(id);
    }

    // add new todo
    public ToDoEntity createNewToDo(ToDoEntity toDo){
        return toDoRepository.save(toDo);
    }

    // update todo
    public ToDoEntity updateToDo(Long id,ToDoEntity toDoDetails){
       ToDoEntity toDo = toDoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Todo id " + id));
       toDo.setTittle(toDoDetails.getTittle());
       toDo.setComplete(toDoDetails.isComplete());
       return toDoRepository.save(toDo);
    }

    private void orElseThrow(Object o) {

    }

    // delete todo
    public void deleteToDo(Long id){
         toDoRepository.deleteById(id);
    }

}
