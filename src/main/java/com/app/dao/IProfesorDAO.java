package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Profesor;

public interface IProfesorDAO extends JpaRepository<Profesor, Integer>{
}
