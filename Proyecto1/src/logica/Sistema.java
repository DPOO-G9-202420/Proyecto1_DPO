package logica;


import persistencia.Archivo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class Sistema {
	private ArrayList<Usuario> usuarios;
    private ArrayList<learningPath> learningPaths;
    
    
	public Sistema() {
		this.usuarios = new ArrayList<>();
		this.learningPaths = new ArrayList<>();

		
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
    	
    
    
 // Método para crear un Learning Path (solo para profesores)
    public void crearLearningPath(Profesor profesor, String titulo, String descripcion, String nivelDificultad) {
        learningPath lp = new learningPath(titulo, descripcion, nivelDificultad, profesor);
        learningPaths.add(lp);
        Archivo.guardarLearningPathEnCSV(lp);
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
    
    
    public void agregarActividad(Profesor profesor, learningPath learningPath) {
    	
    	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);
        boolean esOpcionalQuiz;
        ArrayList<String> opciones=new ArrayList<String>();
        

        if (learningPath != null && learningPath.getProfesor().equals(profesor)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("¿Qué tipo de actividad deseas agregar? (1. Quiz, 2. Tarea, 3. Revisar Recurso)");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: // Agregar Quiz
                	System.out.println("Ingrese el título del Quiz:");
                    String nombreQuiz = scanner.nextLine();

                    System.out.println("Ingrese la descripción del Quiz:");
                    String descripcionQuiz = scanner.nextLine();

                    System.out.println("Ingrese el objetivo del Quiz:");
                    String objetivoQuiz = scanner.nextLine();

                    System.out.println("Ingrese el nivel de dificultad:");
                    String nivelDificultadQuiz = scanner.nextLine();

                    System.out.println("Ingrese la duración en minutos:");
                    int duracionQuiz = scanner.nextInt();
                    scanner.nextLine(); 

                    System.out.println("Ingrese la fecha límite (dd/mm/yyyy):");
                    String fechaString = scanner.nextLine();

                    System.out.println("¿Es opcional? (si/no):");
                    String esOpcionalQuizTxt = scanner.nextLine();

                    if (esOpcionalQuizTxt.equals("si")) {
                        esOpcionalQuiz = true;
                    } else {
                        esOpcionalQuiz = false;
                    }

                    System.out.println("¿Cuántas opciones tendrá el Quiz?");
                    int numOpciones = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 1; i <= numOpciones; i++) {
                        System.out.println("Escribe la opción " + i + ":");
                        String opcionQuiz = scanner.nextLine();
                        opciones.add(opcionQuiz);
                    }

                    System.out.println("¿Cuál es el índice de la opción correcta? (entero):");
                    int iOpcionCorrecta = scanner.nextInt();
             
                    Actividad prerequisitoSugerido=learningPath.getUltimaActividad();
                    Quiz quiz = new Quiz(nombreQuiz, descripcionQuiz, objetivoQuiz,nivelDificultadQuiz, duracionQuiz, fechaString,esOpcionalQuiz,prerequisitoSugerido,opciones,iOpcionCorrecta);
                    learningPath.agregarActividad(quiz);
                    System.out.println("Quiz agregado con éxito.");
                    
                    break;
                case 2: // Agregar Tarea
                    System.out.println("Ingrese el título de la Tarea:");
                    String tituloTarea = scanner.nextLine();
                    System.out.println("Ingrese la descripción de la Tarea:");
                    String descripcionTarea = scanner.nextLine();
                    System.out.println("Ingrese el nivel de dificultad:");
                    String nivelDificultadTarea = scanner.nextLine();
                    System.out.println("Ingrese la duración en minutos:");
                    int duracionTarea = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.println("Ingrese las instrucciones de la Tarea:");
                    String instruccionesTarea = scanner.nextLine();
                    System.out.println("Ingrese el medio de entrega:");
                    String medioEntrega = scanner.nextLine();
                    //Tarea tarea = new Tarea(tituloTarea, descripcionTarea, nivelDificultadTarea, duracionTarea, instruccionesTarea, medioEntrega);
                    //learningPath.agregarActividad(tarea);
                    System.out.println("Tarea agregada con éxito.");
                    
                    break;
                case 3: // Agregar Revisión de Recurso
                    System.out.println("Ingrese el título del recurso:");
                    String tituloRecurso = scanner.nextLine();
                    System.out.println("Ingrese la descripción del recurso:");
                    String descripcionRecurso = scanner.nextLine();
                    System.out.println("Ingrese el nivel de dificultad:");
                    String nivelDificultadRecurso = scanner.nextLine();
                    System.out.println("Ingrese la duración en minutos:");
                    int duracionRecurso = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.println("Ingrese el tipo de recurso (video, libro, pdf, etc.):");
                    String tipoRecurso = scanner.nextLine();
                    System.out.println("Ingrese el enlace al recurso:");
                    String enlaceRecurso = scanner.nextLine();
                    //RevisarRecurso recurso = new RevisarRecurso(tituloRecurso, descripcionRecurso, nivelDificultadRecurso, duracionRecurso, tipoRecurso, enlaceRecurso);
                    //learningPath.agregarActividad(recurso);
                    System.out.println("Recurso agregado con éxito.");
                    
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } else {
            System.out.println("No tienes permisos para editar este Learning Path o no existe.");
        }
        
    }

    // BUSCAR
    public learningPath buscarLearningPathPorTitulo(String titulo) {
        for (learningPath lp : learningPaths) {
            if (lp.getTitulo().equalsIgnoreCase(titulo)) {
                return lp;
            }
        }
        return null;
    }
  
    public ArrayList<learningPath> obtenerLearningPaths() {
        return learningPaths;
    }
    
    private Usuario buscarUsuario(String login) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login)) {
                return u;
            }
        }
        return null;
    }
    public Usuario buscarProfesorPorNombre(String login) {
        for (Usuario usuario : usuarios) {
            if (usuario.getLogin().equals(login)) {
                return usuario;
            }
        }
        return null;
    }



}
    


