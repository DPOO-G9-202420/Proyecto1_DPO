package logica;

import java.util.ArrayList;
import java.util.Date;

public class Quiz extends Actividad{
	private ArrayList<String> opciones;
	private String preguntaQuiz;
	private int iOpcionCorrecta;
	private int numOpciones;
	
	public Quiz(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			String fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido, String preguntaQuiz, int numOpciones, ArrayList<String> opciones, int iOpcionCorrecta) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.opciones=opciones;
		this.iOpcionCorrecta=iOpcionCorrecta;
		this.preguntaQuiz=preguntaQuiz;
		this.numOpciones=numOpciones;
		
	}

	@Override
    public void iniciarActividad() {
    }

    public int getNumOpciones() {
        return numOpciones;
    }

    public ArrayList<String> getPreguntas() {
        return opciones;
    }
    
    public int getCantidadPreguntas() {
        return opciones.size();
    }
    
    public String getPregunta() {
    	return preguntaQuiz;
    }
    
    public int getIOpcionCorrecta() {
    	return iOpcionCorrecta;
    }
    
    
    @Override
    public String getTipo() {
        return "Quiz";
    }
	

	
}