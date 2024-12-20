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

import logica.Estudiante;

public class EstudianteTest {
	private Estudiante estudiante1;
	
	
	@BeforeEach
	void setUp() {
		estudiante1= new Estudiante("s.vergara","roli2006");
	}
	
	@Test
	void testGetTipoUsuario() {
		assertEquals("estudiante", estudiante1.getTipoUsuario());
	}
	
	@Test 
	void testGetLogin() {
		assertEquals("s.vergara",estudiante1.getLogin());
	}

	@Test
	void testGetContraseña() {
		assertEquals("roli2006", estudiante1.getContrasena());
	}
}

