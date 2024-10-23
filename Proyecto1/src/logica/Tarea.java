package logica;

import java.util.ArrayList;
import logica.Actividad;

public class Tarea extends Actividad{
	private String metodoEntrega;
	private String estado;

	public Tarea(String metodoEntrega, String estado, String titulo, String descripcion, int dificultad, int duracion) {
		super();
		this.metodoEntrega=metodoEntrega;
		this.estado=estado;
	}
	
	

}
