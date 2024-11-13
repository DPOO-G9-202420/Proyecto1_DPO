package logica;

import java.util.ArrayList;
import java.util.Date;

import logica.Actividad;

public class Tarea extends Actividad{
	private String metodoEntrega;
	private String instruccionesTarea;
	private String estado;
	
	
	public Tarea(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			String fechaLimite, boolean esOpcional,String instruccionesTarea, String metodoEntrega, Actividad prerequisitoSugerido) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.metodoEntrega=metodoEntrega;
		this.estado=estado;
		this.instruccionesTarea=instruccionesTarea;
		
	}
	
	public String getInstrucciones() {
		return instruccionesTarea;
	}
	
	public String getMetodo() {
		return metodoEntrega;
	}
	
	@Override
    public String getTipo() {
        return "Tarea";
    }


	
	

}
