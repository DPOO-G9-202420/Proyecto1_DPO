package logica;

import java.util.ArrayList;
import java.util.Date;

public class learningPath {
	private String titulo;
	private String descripcion;
	private String nivelDificultad;
	private int duracionMins;
	private double calificacion;
	private Date fechaCreacion;
	private Date fechaModificacion;
	private String version;
	private ArrayList<Actividad> actividades;
	
	public learningPath(String titulo, String descripcion, String nivelDificultad, int duracionMins, float calificacion,
			Date fechaCreacion, Date fechaModificacion, String version) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.nivelDificultad = nivelDificultad;
		this.duracionMins = duracionMins;
		this.calificacion = calificacion;
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaModificacion;
		this.version = version;
		this.actividades=new ArrayList<>();
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getNivelDificultad() {
		return nivelDificultad;
	}
	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
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
	public void setCalificacion(float calificacion) {
		this.calificacion = calificacion;
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
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	
	

}
