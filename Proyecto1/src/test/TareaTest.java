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

import logica.Tarea;
import logica.Actividad;


public class TareaTest {
	private Tarea tarea1;
	
	@BeforeEach
	void Setup() {
		tarea1= new Tarea("lectura 1", "primeras 10 páginas del libro", "aprender sobre la programación 1.0", "facil", 20, "2024-11-11", true, null, "leer las primeras 10 paginas", "bloque neon");
	}
	
	@Test
	void testGetInstrucciones() {
		assertEquals("leer las primeras 10 paginas", tarea1.getInstrucciones());
	}
	
	@Test
	void testGetMetodo() {
		assertEquals("bloque neon", tarea1.getMetodo());
	}
	
	@Test
	void testgetTipo() {
		assertEquals("Tarea", tarea1.getTipo());
	}
}
