package logica;

import java.util.ArrayList;
//import java.util.List;
import java.util.Date;

public class learningPath {
	private String titulo;
	private String descripcion;
	private String nivelDificultad;
	private int duracionMins;
	private double calificacion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private int version;
	private Profesor profesor;
	private ArrayList<Actividad> actividades;
	
	public learningPath(String titulo, String descripcion, String nivelDificultad, Profesor profesor) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nivelDificultad = nivelDificultad;
		this.profesor=profesor;
		this.duracionMins = 0;
		this.calificacion = 0.0;
		this.fechaCreacion = new Date();
		this.fechaModificacion = new Date();
		this.version = 1;
		this.actividades=new ArrayList<>();
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
		actualizarFechaModificacion();
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
		actualizarFechaModificacion();
	}
	public String getNivelDificultad() {
		return nivelDificultad;
	}
	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
		actualizarFechaModificacion();
	}
	public Profesor getProfesor() {
		return profesor;
	}
	public void setProfesor(Profesor profesor) {
		this.profesor=profesor;
	}
	public int getDuracionMins() {
		return duracionMins;
	}
	public void setDuracionMins(int duracionMins) {
		this.duracionMins = duracionMins;
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double nuevaCalificacion) {
		if (nuevaCalificacion < 0|| nuevaCalificacion>5) {
			throw new IllegalArgumentException("La calificacion debe estar entre 0 y 5");
		}
		this.calificacion = nuevaCalificacion;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public ArrayList<Actividad> getActividades(){
		return actividades;
	}
	
	public void agregarActividad(Actividad actividad) {
		actividades.add(actividad);
		actualizarFechaModificacion();
	}
	public boolean eliminarActividad(Actividad actividad) {
		boolean eliminado= actividades.remove(actividad);
		if (eliminado) {
			actualizarFechaModificacion();
		}
		return eliminado;
	}
	public int calcularDuracionLearningPath() {
		int duracionTotal = 0;
	    for (Actividad actividad : actividades) {
	        duracionTotal += actividad.getDuracion();
	    }
	    return duracionTotal;
	}
	 private void actualizarFechaModificacion() {
	        this.fechaModificacion = new Date();
	 }
}
