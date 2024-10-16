package logica;

public class Profesor extends Usuario{

	public Profesor(String login, String contrasena) {
		super(login, contrasena);
		// TODO Auto-generated constructor stub
	}

	@Override
    public String getTipoUsuario() {
        return "profesor";
	}
}
