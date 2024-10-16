package logica;

public abstract class Usuario {
	protected String login;
	protected String contrasena;
	protected String tipoUsuario;
	
	
	public Usuario(String login, String contrasena, String tipoUsuario) {
		super();
		this.login = login;
		this.contrasena = contrasena;
		this.tipoUsuario = tipoUsuario;
		
		
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	
	
}
