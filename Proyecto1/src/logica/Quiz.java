package logica;

import java.util.ArrayList;
import java.util.Date;

public class Quiz extends Actividad{
	private ArrayList<Pregunta> preguntas;
	private float calificacionMinima;
	
	public Quiz(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			String fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.preguntas=preguntas;
		this.calificacionMinima=calificacionMinima;
		
	}

	@Override
    public void iniciarActividad() {
        // Implementación de la lógica para realizar el quiz
    }

    public float getCalificacionMinima() {
        return calificacionMinima;
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas;
    }
	

	
}