package logica;

import java.util.Date;

public abstract class Actividad {
	protected String nombre;
	protected String descripcion;
	protected String objetivo;
	protected String nivelDificultad;
	protected int duracion;
	protected String fechaLimite;
	protected boolean esOpcional;
	protected boolean estado;
	protected Actividad prerequisitoSugerido;
	
	public Actividad(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion, String fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido) {
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
	public abstract String getTipo(); // Método abstracto para obtener el tipo de actividad
	
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
	public String getFechaLimite() {
		return fechaLimite;
	}
	public void setFechaLimite(String fechaLimite) {
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
	
    public boolean prerequisitoCompletado() {
        if (prerequisitoSugerido == null) {
            return true; 
        }
        return prerequisitoSugerido.isEstado();
    }
	
    public void iniciarActividad() {
    	if (!prerequisitoCompletado()) {
    		System.out.println("No has completado el prerequisito sugerido");
    	} else {
    		System.out.println("Has iniciado tu actividad");
    		
    	}
    }
    public void completarActividad() {
        this.estado = true;
        System.out.println("Actividad completada.");
    }

    @Override
    public String toString() {
        return ("Titulo: "+this.getNombre()+"\nDescripción: "+ this.getDescripcion()+"\n Dificultad: "+this.getNivelDificultad()+"\n Es opcional: "+this.isEsOpcional());
    }
    
    
    
}
