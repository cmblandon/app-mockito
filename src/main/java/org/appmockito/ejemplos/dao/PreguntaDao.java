package org.appmockito.ejemplos.dao;

import java.util.List;

public interface PreguntaDao {
    List<String> findPreguntasPorExamenId(Long id);

    void guardarVarias(List<String> preguntas);
}
