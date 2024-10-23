package logica;

import java.util.ArrayList;
import java.util.Date;

import logica.Actividad;

public class Tarea extends Actividad{
	private String metodoEntrega;
	private String estado;
	
	
	public Tarea(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			Date fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.metodoEntrega=metodoEntrega;
		this.estado=estado;
	}


	
	

}
