package org.appmockito.ejemplos.services;

import org.appmockito.ejemplos.dao.ExamenDao;
import org.appmockito.ejemplos.dao.PreguntaDao;
import org.appmockito.ejemplos.models.Examen;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExamenServiceImplTest {
    @Mock
    ExamenDao examenDao;
    @Mock
    PreguntaDao preguntaDao;

    @InjectMocks
    ExamenServiceImpl service;

    /*@BeforeEach -> este meétodo lo remplaza la anotación @ExtendWith(MockitoExtension.class)

    void setUp() {
        MockitoAnnotations.openMocks(this);
       /* examenDao = mock(ExamenDao.class);
        preguntaDao = mock(PreguntaDao.class);
        service = new ExamenServiceImpl(examenDao, preguntaDao);
    }*/

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

    @Test
    void testPreguntasExamenVerificar() {
        when(examenDao.findAll()).thenReturn(Datos.EXAMENES);
        when(preguntaDao.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matemáticas");
        assertEquals(5, examen.getPreguntas().size());
        assertTrue(examen.getPreguntas().contains("aritmética"));
        verify(examenDao).findAll();
        verify(preguntaDao).findPreguntasPorExamenId(anyLong());
    }

    @Test
    void testNoExisteExamenVerificar() {
        when(examenDao.findAll()).thenReturn(Collections.emptyList());
        when(preguntaDao.findPreguntasPorExamenId(anyLong())).thenReturn(Datos.PREGUNTAS);
        Examen examen = service.findExamenPorNombreConPreguntas("Matemáticas");
        assertNull(examen);
        verify(examenDao).findAll();
        verify(preguntaDao).findPreguntasPorExamenId(5L);
    }

    @Test
    void testGuardarExamen() {
        Examen newExamen = Datos.EXAMEN;
        newExamen.setPreguntas(Datos.PREGUNTAS);
        when(examenDao.guardar(any(Examen.class))).thenReturn(Datos.EXAMEN);
        Examen examen = service.guardar(newExamen);
        assertNotNull(examen.getId());
        assertEquals(8L, examen.getId());
        assertEquals("Física", examen.getNombre());
        verify(examenDao).guardar(any(Examen.class));
        verify(preguntaDao).guardarVarias(anyList());
    }
}