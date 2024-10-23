package logica;

import java.util.ArrayList;
import java.util.Date;

public class Quiz extends Actividad{
	private ArrayList<Pregunta> preguntas;
	private float calificacionMinima;
	
	public Quiz(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			Date fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.preguntas=preguntas;
		this.calificacionMinima=calificacionMinima;
		
	}

	
	

	
}