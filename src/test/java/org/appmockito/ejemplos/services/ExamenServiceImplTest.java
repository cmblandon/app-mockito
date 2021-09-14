package org.appmockito.ejemplos.services;

import org.appmockito.ejemplos.dao.ExamenDao;
import org.appmockito.ejemplos.dao.PreguntaDao;
import org.appmockito.ejemplos.models.Examen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamenServiceImplTest {

    ExamenDao examenDao;
    ExamenService service;
    PreguntaDao preguntaDao;

    @BeforeEach
    void setUp() {
        examenDao = mock(ExamenDao.class);
        preguntaDao = mock(PreguntaDao.class);
        service = new ExamenServiceImpl(examenDao, preguntaDao);
    }

    @Test
    void findExamenPorNombre() {
        when(examenDao.findAll()).thenReturn(Datos.EXAMENES);
        Optional<Examen> examen = service.findExamenPorNombre("Matemáticas");
        assertTrue(examen.isPresent());
        assertEquals(5, examen.orElseThrow(ArithmeticException::new).getId());
        assertEquals("Matemáticas", examen.get().getNombre());
    }

    @Test
    void findExamenPorNombreListaVacia() {
        List<Examen> datos = Collections.emptyList();
        when(examenDao.findAll()).thenReturn(datos);
        Optional<Examen> examen = service.findExamenPorNombre("Matemáticas");
        assertFalse(examen.isPresent());
    }

    @Test
    void testPreguntasExamen() {
        when(examenDao.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaDao.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matemáticas");
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("aritmética"));
    }
}