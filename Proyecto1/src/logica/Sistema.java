package logica;


import persistencia.Archivo;

import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileReader;
//import java.io.FileWriter;
import java.util.List;


public class Sistema {
	private ArrayList<Usuario> usuarios;
    private ArrayList<learningPath> learningPaths;
    private List<String> profesores;
    
    
	public Sistema() {
		this.usuarios = new ArrayList<>();
		this.learningPaths = new ArrayList<>();
		this.profesores=new ArrayList<String>();
		
	}
    

    public boolean registrarUsuario(String login, String contrasena, String tipoUsuario) {
        if (buscarUsuario(login) == null) {
            Usuario nuevoUsuario;
            if (tipoUsuario.equals("profesor")) {
                nuevoUsuario = new Profesor(login, contrasena);
            } else if (tipoUsuario.equals("estudiante")) {
                nuevoUsuario = new Estudiante(login, contrasena);
            } else {
                return false;
            }
            
            usuarios.add(nuevoUsuario);
            Archivo.escribirUsuarioCSV(nuevoUsuario);
            
            return true;
        }
        return false;
    }

    public Usuario iniciarSesion(String login, String contrasena) {
        Usuario usuario = buscarUsuario(login);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            return usuario;
        }
        return null;
    }
    
    public void cargarUsuariosCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.csv"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String username = partes[0];
                    String password = partes[1];
                    String tipoUsuario = partes[2];
                    Usuario usuario;
                    if (tipoUsuario.equals("profesor")) {
                        usuario = new Profesor(username, password);
                        
                    } else if (tipoUsuario.equals("estudiante")) {
                        usuario = new Estudiante(username, password);
                    } else {
                        System.out.println("Tipo de usuario no válido en el archivo CSV: " + tipoUsuario);
                        continue;
                    }
                    usuarios.add(usuario);  // Agregar usuario a la lista en memoria
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
    	
    

    private Usuario buscarUsuario(String login) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }
    
 // Método para crear un Learning Path (solo para profesores)
    public void crearLearningPath(Profesor profesor, String titulo, String descripcion, String nivelDificultad) {
        learningPath lp = new learningPath(titulo, descripcion, nivelDificultad, profesor);
        learningPaths.add(lp);
        Archivo.guardarLearningPathEnCSV(lp);
    }
    
    // Método para obtener todos los Learning Paths
    public List<learningPath> obtenerLearningPaths() {
        return learningPaths;
    }
    
    public void cargarLearningPathsDesdeCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader("lp.csv"))) {
            String linea;

            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");

                // Obtener datos del Learning Path
                String titulo = campos[0];
                String descripcion = campos[1];
                String nivelDificultad = campos[2];
                String loginCreador = campos[3];

                // Buscar al creador en la lista de profesores
                Profesor creador = (Profesor) buscarProfesorPorNombre(loginCreador);

                // Crear instancia de Learning Path
                learningPath lp = new learningPath(titulo, descripcion, nivelDificultad, creador);

                // Agregar el Learning Path a la lista
                learningPaths.add(lp);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los Learning Paths: " + e.getMessage());
        }
    }
    
    public Usuario buscarProfesorPorNombre(String login) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                return usuario;
            }
        }
        return null;  // Si no se encuentra el profesor, podrías lanzar una excepción o manejar el caso
    }
    

}
