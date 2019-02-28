package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IInfanteDAO;
import com.app.model.Infante;
import com.app.service.IInfanteService;
@Service
public class InfanteServiceImpl implements IInfanteService{
	@Autowired
	IInfanteDAO dao;
	
	@Override
	public List<Infante> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Infante create(Infante obj) {
		
		return dao.save(obj);
	}

	@Override
	public Infante findById(int id) {
		
		return dao.findById(id).orElse(null);
	}

	@Override
	public Infante update(Infante obj) {
		return dao.save(obj);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
		
		
	}

}
