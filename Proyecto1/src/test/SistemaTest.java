package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Sistema;
import logica.Estudiante;
import logica.Usuario;
import logica.Profesor;
import logica.learningPath;

public class SistemaTest {
	private Sistema sistema;
	private Usuario usuario;
	private Profesor profesor;
	
	@BeforeEach
	public void SetUp() {
		this.sistema=new Sistema();	
	}
	
	@Test
	public void testRegistrarUsuarioNuevo() {
		boolean registrado= sistema.registrarUsuario("d.vergara112", "dani2004", "profesor");
		assertTrue(registrado);
		
		usuario=sistema.buscarProfesorPorNombre("d.vergara112");
		assertNotNull(usuario);
		assertEquals("d.vergara112", usuario.getLogin());
	}
	
	@Test
	public void testRevisarUsuarioQueYaExiste() {
		sistema.registrarUsuario("d.vergara112", "dani2004", "profesor");
		boolean registrado= sistema.registrarUsuario("d.vergara112", "dani2004", "profesor");
		assertFalse(registrado);	
	}
	
	@Test
	public void testInicioSesion() {
		sistema.registrarUsuario("d.vergara112", "dani2004", "profesor");
		usuario= sistema.iniciarSesion("d.vergara112", "dani2004");
		assertEquals("d.vergara112", usuario.getLogin());
	}
	
	@Test
	public void testInicioSesionContraseñaFallida() {
		sistema.registrarUsuario("d.vergara112", "dani2004", "profesor");
		usuario= sistema.iniciarSesion("d.vergara112", "1234");
		assertNull(usuario);
	}
	
	@Test
	public void testCrearLearningPath() {
		profesor= new Profesor("mc.aponte2", "macalister14");
		sistema.registrarUsuario(profesor.getLogin(), profesor.getContrasena(), "profesor");
		
		sistema.crearLearningPath(profesor, "introducción a la programación", "primer curso ingenieria", "facil");
		ArrayList<learningPath> learningPaths=sistema.obtenerLearningPaths();
		
		assertEquals(1,learningPaths.size());
		assertEquals("introducción a la programación", learningPaths.get(0).getTitulo());
	}
	
	@Test 
	public void testBuscarLearningPath() {
		profesor= new Profesor("mc.aponte2", "macalister14");
		sistema.registrarUsuario(profesor.getLogin(), profesor.getContrasena(), "mc.aponte2");
		
		sistema.crearLearningPath(profesor, "introducción a la programación", "primer curso ingenieria", "facil");
		learningPath buscarLearningPath= sistema.buscarLearningPathPorTitulo("introducción a la programación");
		assertEquals("introducción a la programación", buscarLearningPath.getTitulo());
		assertEquals("primer curso ingenieria", buscarLearningPath.getDescripcion());
	}
	
}
