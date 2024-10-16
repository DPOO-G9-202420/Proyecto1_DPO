package logica;

import java.util.ArrayList;

public class Sistema {
	private ArrayList<Usuario> usuarios;
    private ArrayList<learningPath> learningPaths;
    
    
	public Sistema() {
		this.usuarios = new ArrayList<>();
		this.learningPaths = new ArrayList<>();
	}
    

	public boolean registrarUsuario(String login, String contrasena, String tipoUsuario) {
	    if (buscarUsuario(login) == null) {
	        if (tipoUsuario.equals("profesor")) {
	            usuarios.add(new Profesor(login, contrasena));
	        } else if (tipoUsuario.equals("estudiante")) {
	            usuarios.add(new Estudiante(login, contrasena));
	        } else {
	            //exception
	            return false;
	        }
	        return true;
	    }
	    return false;
	}

    public Usuario iniciarSesion(String username, String contrasena) {
        Usuario usuario = buscarUsuario(username);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }

    private Usuario buscarUsuario(String login) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }
    

}
