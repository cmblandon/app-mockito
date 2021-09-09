package org.appmockito.ejemplos.services;

import org.appmockito.ejemplos.models.Examen;

public interface ExamenService {
    Examen findExamenPorNombre(String nombre);
}
