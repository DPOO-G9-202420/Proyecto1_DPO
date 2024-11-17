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

import logica.Profesor;


public class ProfesorTest {
	private Profesor profesor1;
	
	@BeforeEach
	void setUp() {
		profesor1= new Profesor("d.vergara112", "dani2004");
	}
	
	@Test
	void testGetTipoUsuario() {
		assertEquals("profesor", profesor1.getTipoUsuario());
	}
	
	@Test
	void testGetLogin() {
		assertEquals("d.vergara112", profesor1.getLogin());
	}
	
	@Test
	void testGetContraseña() {
		assertEquals("dani2004", profesor1.getContrasena());
	}
	
}
