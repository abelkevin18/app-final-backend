package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IProfesorDAO;
import com.app.model.Profesor;
import com.app.service.IProfesorService;

@Service
public class ProfesorServiceImpl implements IProfesorService{
	@Autowired
	IProfesorDAO dao;
	
	@Override
	public List<Profesor> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Profesor create(Profesor obj) {
		
		return dao.save(obj);
	}

	@Override
	public Profesor findById(int id) {
		
		return dao.findById(id).orElse(null);
	}

	@Override
	public Profesor update(Profesor obj) {
		
		return dao.save(obj);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
		
	}

}