package org.appmockito.ejemplos.dao;

import org.appmockito.ejemplos.models.Examen;

import java.util.List;

public interface ExamenDao {
    List<Examen> findAll();
}
