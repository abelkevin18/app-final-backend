package com.app.service;

import java.util.List;

import com.app.model.Resultadocuestionario;

public interface IResultadocuestionarioService extends ICRUD<Resultadocuestionario> {
	List<Resultadocuestionario> getResultados();
}
