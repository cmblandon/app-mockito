package org.appmockito.ejemplos.dao;

import org.appmockito.ejemplos.models.Examen;

import java.util.Arrays;
import java.util.List;

public class ExamenDaoImpl implements ExamenDao {

    @Override
    public List<Examen> findAll() {
        return Arrays.asList(new Examen(5L, "Matemáticas"), new Examen(6L, "Lenguaje"),
                new Examen(7L, "Historia"));
    }
}