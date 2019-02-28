package com.app.service;

import java.util.List;

public interface ICRUD <T>{
	List<T> findAll();

	T create(T obj);

	T findById(int id);

	T update(T obj);

	void delete(int id);
}
