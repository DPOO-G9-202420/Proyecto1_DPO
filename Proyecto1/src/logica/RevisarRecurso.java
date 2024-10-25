package logica;

import java.util.Date;

public class RevisarRecurso extends Actividad{
	private String tipoRecurso; 
    private String enlaceRecurso;

	public RevisarRecurso(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			String fechaLimite, boolean esOpcional, String tipoRecurso, String enlaceRecurso, Actividad prerequisitoSugerido) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.tipoRecurso=tipoRecurso;
		this.enlaceRecurso=enlaceRecurso;
		
		
	}
	
	

}


