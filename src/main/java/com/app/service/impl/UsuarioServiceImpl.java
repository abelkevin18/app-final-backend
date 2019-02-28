package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IUsuarioDAO;
import com.app.model.Usuario;
import com.app.service.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService{
	@Autowired
	IUsuarioDAO dao;

	@Override
	public List<Usuario> findAll() {
		
		return (List<Usuario>) dao.findAll();
	}

	@Override
	public Usuario create(Usuario obj) {
		
		return dao.save(obj);
	}

	@Override
	public Usuario findById(int id) {
		
		return dao.findById(id).get();
	}

	@Override
	public Usuario update(Usuario obj) {
		
		return dao.save(obj);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
		
	}
}
