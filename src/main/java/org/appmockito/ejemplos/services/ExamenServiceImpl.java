package org.appmockito.ejemplos.services;

import org.appmockito.ejemplos.dao.ExamenDao;
import org.appmockito.ejemplos.dao.PreguntaDao;
import org.appmockito.ejemplos.models.Examen;

import java.util.List;
import java.util.Optional;

public class ExamenServiceImpl implements ExamenService {
    private ExamenDao examenDao;
    private PreguntaDao preguntaDao;

    public ExamenServiceImpl(ExamenDao examenDao, PreguntaDao preguntaDao) {
        this.examenDao = examenDao;
        this.preguntaDao = preguntaDao;
    }

    @Override
    public Optional<Examen> findExamenPorNombre(String nombre) {
        return examenDao.findAll()
                .stream()
                .filter(e -> e.getNombre().contains(nombre))
                .findFirst();
    }

    @Override
    public Examen findExamenPorNombreConPreguntas(String nombre) {
        Optional<Examen> examenOptional = findExamenPorNombre(nombre);
        Examen examen = null;
        examen = examenOptional.orElseThrow(IllegalArgumentException::new);
        if (examenOptional.isPresent()) {
            List<String> preguntas = preguntaDao.findPreguntasPorExamenId(examen.getId());
            examen.setPreguntas(preguntas);
        }
        return examen;
    }
}
