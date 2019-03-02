package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Programa;

public interface IProgramaDAO extends JpaRepository<Programa, Integer>{
}
