package logica;

public class Examen extends Actividad{

	public Examen(String nombre, String descripcion, String objetivo, String nivelDificultad, int duracion,
			String fechaLimite, boolean esOpcional, Actividad prerequisitoSugerido) {
		super(nombre, descripcion, objetivo, nivelDificultad, duracion, fechaLimite, esOpcional, prerequisitoSugerido);

	}
	
	@Override
    public String getTipo() {
        return "Examen";
    }

}
