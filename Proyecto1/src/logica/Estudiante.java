package logica;

public class Estudiante extends Usuario{

	public Estudiante(String login, String contrasena) {
		super(login, contrasena);
		// TODO Auto-generated constructor stub
	}
    
	@Override
    public String getTipoUsuario() {
        return "estudiante";
        
	}
	
}
