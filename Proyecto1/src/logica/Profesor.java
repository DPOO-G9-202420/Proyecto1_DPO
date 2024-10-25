package logica;

public class Profesor extends Usuario{

	public Profesor(String login, String contrasena) {
		super(login, contrasena);
	}

	@Override
    public String getTipoUsuario() {
        return "profesor";
	}
}
