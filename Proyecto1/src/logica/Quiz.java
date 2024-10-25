package logica;

import java.util.ArrayList;
import java.util.Date;

public class Quiz extends Actividad{
	private ArrayList<String> opciones;
	private String preguntaQuiz;
	private int iOpcionCorrecta;
	private float calificacionMinima;
	
	public Quiz(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			String fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido, String preguntaQuiz, ArrayList<String> opciones, int iOpcionCorrecta) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.opciones=opciones;
		this.iOpcionCorrecta=iOpcionCorrecta;
		this.preguntaQuiz=preguntaQuiz;
		this.calificacionMinima=calificacionMinima;
		
	}

	@Override
    public void iniciarActividad() {
    }

    public float getCalificacionMinima() {
        return calificacionMinima;
    }

    public ArrayList<String> getPreguntas() {
        return opciones;
    }
	

	
}