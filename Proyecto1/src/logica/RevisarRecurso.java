package logica;

import java.util.Date;

public class RevisarRecurso extends Actividad{
	private String tipoRecurso; 
    private String enlaceRecurso;

	public RevisarRecurso(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			Date fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.tipoRecurso=tipoRecurso;
		this.enlaceRecurso=enlaceRecurso;
		
		
	}
	
	

}

