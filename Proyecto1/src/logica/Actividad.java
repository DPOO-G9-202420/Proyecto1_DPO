package logica;

import java.util.Date;

public abstract class Actividad {
	protected String nombre;
	protected String descripcion;
	protected String objetivo;
	protected String nivelDificultad;
	protected int duracion;
	protected Date fechaLimite;
	protected boolean esOpcional;
	protected boolean estado;
	protected Actividad prerequisitoSugerido;
	
	public Actividad(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion, Date fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido) {
		super();
		this.nombre=nombre;
		this.descripcion=descripcion;
		this.objetivo=objetivo;
		this.nivelDificultad=nivelDificultad;
		this.duracion=duracion;
		this.fechaLimite=fechaLimite;
		this.esOpcional=esOpcional;
		this.estado=false;
		this.prerequisitoSugerido=prerequisitoSugerido;
	}
		
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getNivelDificultad() {
		return nivelDificultad;
	}
	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}
	public Date getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}
	public boolean isEsOpcional() {
		return esOpcional;
	}
	public void setEsOpcional(boolean esOpcional) {
		this.esOpcional = esOpcional;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public Actividad getPrerequisitoSugerido() {
		return prerequisitoSugerido;
	}
	public void setPrerequisitoSugerido(Actividad prerequisitoSugerido) {
		this.prerequisitoSugerido = prerequisitoSugerido;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getDuracion() {
		return duracion;
	}
	
	// Método para verificar si el prerrequisito ha sido completado
    public boolean prerequisitoCompletado() {
        if (prerequisitoSugerido == null) {
            return true; // No hay prerrequisito sugerido
        }
        return prerequisitoSugerido.isEstado();
    }
	
	//Método para iniciar actividad
    public void iniciarActividad() {
    	if (!prerequisitoCompletado()) {
    		System.out.println("No has completado el prerequisito sugerido");
    	} else {
    		System.out.println("Has iniciado tu actividad");
    		
    	}
    }
 // Método para completar la actividad
    public void completarActividad() {
        this.estado = true;
        System.out.println("Actividad completada.");
    }
 // Método toString para mostrar la actividad
    @Override
    public String toString() {
        return String.format("Actividad:\nDescripción\nObjetivo:\nDificultad:\n" +
                             "Duración:\nFecha Límite:\nOpcional:\nCompletada:\n" +
                             "Prerequisito Sugerido:",
                nombre, descripcion, objetivo, nivelDificultad, duracion,
                (fechaLimite != null ? fechaLimite.toString() : "Sin límite"),
                (esOpcional ? "Si" : "No"),
                (estado ? "Si" : "No"),
                (prerequisitoSugerido != null ? prerequisitoSugerido.getNombre() : "Ninguno"));
    }
    
    
    
}
