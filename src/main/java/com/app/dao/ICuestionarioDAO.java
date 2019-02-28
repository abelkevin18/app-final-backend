package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Cuestionario;

public interface ICuestionarioDAO extends JpaRepository<Cuestionario, Integer>{
}
