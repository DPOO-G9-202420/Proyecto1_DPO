package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.Sistema;
import logica.Estudiante;
import logica.Usuario;
import logica.Profesor;

public class SistemaTest {
	private Sistema sistema;
	private Usuario usuario;
	private Estudiante estudiante;
	private Profesor profesor;
	
	@BeforeEach
	public void SetUp() {
		this.sistema=new Sistema();
		estudiante= new Estudiante("mc.aponte", "1234");
		profesor=new Profesor ("d.vergara112", "dani2004");
	}
	
	@Test
	public void getTipoUsuarioTest() {

	}
	
	
}
