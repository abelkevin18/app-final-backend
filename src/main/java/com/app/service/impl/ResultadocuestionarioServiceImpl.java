package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IResultadocuestionarioDAO;
import com.app.model.Resultadocuestionario;
import com.app.service.IResultadocuestionarioService;
@Service
public class ResultadocuestionarioServiceImpl implements IResultadocuestionarioService{
	@Autowired
	IResultadocuestionarioDAO dao;
	
	@Override
	public List<Resultadocuestionario> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Resultadocuestionario create(Resultadocuestionario obj) {
		
		return dao.save(obj);
	}

	@Override
	public Resultadocuestionario findById(int id) {
		
		return dao.findById(id).orElse(null);
	}

	@Override
	public Resultadocuestionario update(Resultadocuestionario obj) {
		
		return dao.save(obj);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
		
	}

}