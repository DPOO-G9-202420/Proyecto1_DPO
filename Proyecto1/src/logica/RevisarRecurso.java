package logica;

import java.util.Date;

public class RevisarRecurso extends Actividad{
	private String tipoRecurso; 
    private String enlaceRecurso;

	public RevisarRecurso(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			String fechaLimite, boolean esOpcional,Actividad prerequisitoSugerido, String tipoRecurso, String enlaceRecurso) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);
		this.tipoRecurso=tipoRecurso;
		this.enlaceRecurso=enlaceRecurso;
		
		
	}
	
	@Override
    public String getTipo() {
        return "RevisarRecurso";
    }
	
	public String getTipoRecurso() {
		return tipoRecurso;
	}
	
	public String getEnlace(){
		return enlaceRecurso;
	}
	
	

}


