package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.learningPath;
import logica.Profesor;
import logica.Actividad;

public class LearningPathTest {
	private learningPath learningPath1;
	private Profesor profesor1;
	private Actividad actividad1;
	private Actividad actividad2;
	
	private static class ActividadEspecifica extends Actividad{
		public ActividadEspecifica(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion, String fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido) {
			super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		}

		@Override
		public String getTipo() {
			return "Especifica";
		}
	}
	
	@BeforeEach
	void setUp() {
		profesor1= new Profesor("mc.aponte2", "macalister14");
		learningPath1= new learningPath("Java basico", "intro a java en eclipse", "facil", profesor1);
	
	}
	
	@Test
	void testAgregarActividad() {
		Actividad actividad1= new ActividadEspecifica("Quiz 1 java", "primer quiz", "aprender java", "facil", 30, "2024-11-11", false, null);
		learningPath1.agregarActividad(actividad1);
		
		assertEquals(1, learningPath1.getActividades().size());
		assertEquals(actividad1, learningPath1.getUltimaActividad());
		
	}
	
	@Test
	void testEliminarActividad() {
		Actividad actividad1= new ActividadEspecifica("Quiz 1 java", "primer quiz", "aprender java", "facil", 30, "2024-11-11", false, null);
		learningPath1.agregarActividad(actividad1);
		
		boolean eliminado=learningPath1.eliminarActividad(actividad1);
		assertTrue(eliminado);
		assertEquals(0, learningPath1.getActividades().size());
		
	}
	
	@Test
	void testCalcularDuracion() {
		Actividad actividad1= new ActividadEspecifica("Quiz 1 java", "primer quiz", "aprender java", "facil", 30, "2024-11-11", false, null);
		Actividad actividad2= new ActividadEspecifica("Tarea 1", "matrices", "aprender de matrices", "intermedio", 20, "2024-12-12", true, null);
		learningPath1.agregarActividad(actividad1);
		learningPath1.agregarActividad(actividad2);
		assertEquals(50, learningPath1.calcularDuracionLearningPath());
		
	}
	
	
	
	
}
