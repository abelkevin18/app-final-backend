package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IProgramaDAO;
import com.app.model.Programa;
import com.app.service.IProgramaService;

@Service
public class ProgramaServiceImpl implements IProgramaService{
	@Autowired
	IProgramaDAO dao;
	
	@Override
	public List<Programa> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Programa create(Programa obj) {
		
		return dao.save(obj);
	}

	@Override
	public Programa findById(int id) {
		
		return dao.findById(id).orElse(null);
	}

	@Override
	public Programa update(Programa obj) {
		
		return dao.save(obj);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
		
	}

}