package logica;

public class Estudiante extends Usuario{

	public Estudiante(String login, String contrasena) {
		super(login, contrasena);
	}
    
	@Override
    public String getTipoUsuario() {
        return "estudiante";
        
	}
	
	
	
}
