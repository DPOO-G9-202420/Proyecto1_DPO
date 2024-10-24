package logica;

import java.util.ArrayList;
import java.util.Date;

public class Quiz extends Actividad{
	private ArrayList<String> opciones;
	private int iOpcionCorrecta;
	private float calificacionMinima;
	
	public Quiz(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			String fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido, ArrayList<String> opciones, int iOpcionCorrecta) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.opciones=opciones;
		this.iOpcionCorrecta=iOpcionCorrecta;
		this.calificacionMinima=calificacionMinima;
		
	}

	@Override
    public void iniciarActividad() {
        // Implementación de la lógica para realizar el quiz
    }

    public float getCalificacionMinima() {
        return calificacionMinima;
    }

    public ArrayList<String> getPreguntas() {
        return opciones;
    }
	

	
}