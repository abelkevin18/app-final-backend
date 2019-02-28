package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Usuario;

public interface IUsuarioDAO extends JpaRepository<Usuario, Integer>{

}
