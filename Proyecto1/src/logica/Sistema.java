package logica;

import java.util.ArrayList;

public class Sistema {
	private ArrayList<Usuario> usuarios;
    private ArrayList<learningPath> learningPaths;
    
    
	public Sistema(ArrayList<Usuario> usuarios, ArrayList<learningPath> learningPaths) {
		this.usuarios = usuarios;
		this.learningPaths = learningPaths;
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

    public Usuario iniciarSesion(String username, String password) {
        Usuario usuario = buscarUsuario(username);
        if (usuario != null && usuario.getContrasena().equals(password)) {
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
