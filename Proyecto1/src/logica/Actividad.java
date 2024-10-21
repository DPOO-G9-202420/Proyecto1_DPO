package logica;

import java.util.Date;

public abstract class Actividad {
	protected String descripcion;
	protected String objetivo;
	protected String nivelDificultad;
	protected int duracion;
	protected Date fechaLimite;
	protected boolean esOpcional;
	protected String estado;
	protected Actividad prerequisitoSugerido;
	
	
	
public int getDuracion() {
	return duracion;
}
}
