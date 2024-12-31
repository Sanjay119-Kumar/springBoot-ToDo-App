package com.mySpringboot.To_Do.App.dao;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ToDoRepository extends JpaRepository<ToDoEntity,Long> {
}
