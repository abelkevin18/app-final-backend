package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.ICuestionarioDAO;
import com.app.model.Cuestionario;
import com.app.service.ICuestionarioService;
@Service
public class CuestionarioServiceImpl implements ICuestionarioService{
	@Autowired
	ICuestionarioDAO dao;
	
	@Override
	public List<Cuestionario> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Cuestionario create(Cuestionario obj) {
		
		return dao.save(obj);
	}

	@Override
	public Cuestionario findById(int id) {
		
		return dao.findById(id).orElse(null);
	}

	@Override
	public Cuestionario update(Cuestionario obj) {
		
		return dao.save(obj);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
		
	}

}