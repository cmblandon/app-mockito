package org.appmockito.ejemplos.services;

import org.appmockito.ejemplos.dao.ExamenDao;
import org.appmockito.ejemplos.models.Examen;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExamenServiceImplTest {

    @Test
    void findExamenPorNombre() {
        ExamenDao examenDao = mock(ExamenDao.class);
        ExamenService service = new ExamenServiceImpl(examenDao);
        List<Examen> datos = Arrays.asList(new Examen(5L, "Matemáticas"), new Examen(6L, "Lenguaje"),
                new Examen(7L, "Historia"));
        when(examenDao.findAll()).thenReturn(datos);
        Optional<Examen> examen = service.findExamenPorNombre("Matemáticas");
        assertTrue(examen.isPresent());
        assertEquals(5, examen.orElseThrow(ArithmeticException::new).getId());
        assertEquals("Matemáticas", examen.get().getNombre());
    }

    @Test
    void findExamenPorNombreListaVacia() {
        ExamenDao examenDao = mock(ExamenDao.class);
        ExamenService service = new ExamenServiceImpl(examenDao);
        List<Examen> datos = Collections.emptyList();
        when(examenDao.findAll()).thenReturn(datos);
        Optional<Examen> examen = service.findExamenPorNombre("Matemáticas");
        assertTrue(examen.isPresent());
        assertEquals(5, examen.orElseThrow(ArithmeticException::new).getId());
        assertEquals("Matemáticas", examen.get().getNombre());
    }
}