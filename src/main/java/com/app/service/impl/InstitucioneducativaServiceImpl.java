package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IInstitucioneducativaDAO;
import com.app.model.Institucioneducativa;
import com.app.service.IInstitucioneducativaService;
@Service
public class InstitucioneducativaServiceImpl implements IInstitucioneducativaService{
	@Autowired
	IInstitucioneducativaDAO dao;
	
	@Override
	public List<Institucioneducativa> findAll() {
		
		return dao.findAll();
	}

	@Override
	public Institucioneducativa create(Institucioneducativa obj) {
		
		return dao.save(obj);
	}

	@Override
	public Institucioneducativa findById(int id) {
		
		return dao.findById(id).orElse(null);
	}

	@Override
	public Institucioneducativa update(Institucioneducativa obj) {
		
		return dao.save(obj);
	}

	@Override
	public void delete(int id) {
		dao.deleteById(id);
		
	}

}