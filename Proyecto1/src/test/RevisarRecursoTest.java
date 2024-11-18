package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.RevisarRecurso;
import logica.Actividad;

public class RevisarRecursoTest {
	private RevisarRecurso revisarRecurso1;
	
	@BeforeEach
	void setUp() {
		revisarRecurso1= new RevisarRecurso("Libro ayuda", "Libro con todos los temas del curso", "Ayudar a los estudiantes por fuera del salón de clase", "intermedio", 50, "2025-01-01", true, null, "libro", "www.libro.com.co");
	}
	
	@Test
	void testGetTipo() {
		assertEquals("RevisarRecurso", revisarRecurso1.getTipo());
	}
	
	@Test
	void testGetTipoRecurso() {
		assertEquals("libro", revisarRecurso1.getTipoRecurso());
	}
	
	@Test 
	void testGetEnlace() {
		assertEquals("www.libro.com.co", revisarRecurso1.getEnlace());
	}
}
