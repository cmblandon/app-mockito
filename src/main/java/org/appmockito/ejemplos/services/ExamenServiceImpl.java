package org.appmockito.ejemplos.services;

import org.appmockito.ejemplos.dao.ExamenDao;
import org.appmockito.ejemplos.models.Examen;

import java.util.Optional;

public class ExamenServiceImpl implements ExamenService {
    private ExamenDao examenDao;

    public ExamenServiceImpl(ExamenDao examenDao) {
        this.examenDao = examenDao;
    }

    @Override
    public Examen findExamenPorNombre(String nombre) {
        Optional<Examen> examenOptional = examenDao.findAll()
                .stream()
                .filter(e -> e.getNombre().contains(nombre))
                .findFirst();
        Examen examen = null;
        if (examenOptional.isPresent()) {
            examen = examenOptional.orElse(null);
        }
        return examen;
    }
}
