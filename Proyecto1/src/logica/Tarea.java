package logica;

import java.util.ArrayList;
import logica.Actividad;

public class Tarea extends Actividad{
	private String metodoEntrega;
	private String estado;

	public Tarea(String metodoEntrega, String estado) {
		//super();
		this.metodoEntrega=metodoEntrega;
		this.estado=estado;
	}
	
	

}
