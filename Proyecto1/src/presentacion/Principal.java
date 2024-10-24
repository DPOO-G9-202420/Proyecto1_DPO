package presentacion;

import logica.Sistema;
import logica.Usuario;
import logica.Profesor;
import logica.Estudiante;
import logica.learningPath;

import java.io.IOException;
import java.text.ParseException;
//import java.util.ArrayList;
import java.util.Scanner;


public class Principal {
	public static void main(String[] args) throws IOException {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        
        sistema.cargarUsuariosCSV();
        sistema.cargarLearningPathsDesdeCSV();
        //sistema.registrarUsuario("d.vergara112", "dani2004", "profesor");
        //sistema.registrarUsuario("mc.aponte2", "macalister14", "profesor");
        //sistema.registrarUsuario("s.vergara", "roli2006", "estudiante");
        //sistema.registrarUsuario("l.moure", "luciamoure", "estudiante");
        
        while (!salir) {
            System.out.println("---Learning Path Recommendation System---");
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    registrarUsuario(sistema, scanner);
                    break;
                case 2:
                    iniciarSesion(sistema, scanner);
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Seleccione una opción válida.");
            }
        }

        scanner.close();
    }
	
	private static void registrarUsuario(Sistema sistema, Scanner scanner) {
        System.out.println("---Registrar usuario---");
        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();
        System.out.print("Tipo de usuario (profesor/estudiante): ");
        String tipoUsuario = scanner.nextLine().toLowerCase();

        if (sistema.registrarUsuario(username, password, tipoUsuario)) {
            System.out.println("Usuario registrado exitosamente.");
        } else {
            System.out.println("El nombre de usuario ya existe o el tipo de usuario no es válido.");
        }
    }
	
	private static void iniciarSesion(Sistema sistema, Scanner scanner) {
        System.out.println("---Iniciar sesión---");
        System.out.print("Nombre de usuario: ");
        String username = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = sistema.iniciarSesion(username, password);
        if (usuario != null) {
            System.out.println("Inicio de sesión exitoso. Bienvenido, " + usuario.getLogin() + " (" + usuario.getTipoUsuario() + ")");
            if (usuario instanceof Profesor) {
                menuProfesor((Profesor) usuario, sistema, scanner);
            } else if (usuario instanceof Estudiante) {
                menuEstudiante((Estudiante) usuario, sistema, scanner);
            }
        } else {
            System.out.println("Credenciales incorrectas. Inténtalo nuevamente.");
        }
    }
	
	// Método para gestionar opciones de profesor
    private static void menuProfesor(Profesor profesor, Sistema sistema, Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("---Menú Profesor---");
            System.out.println("1. Crear Learning Path");
            System.out.println("2. Ver Learning Paths");
            System.out.println("3. Cerrar sesión");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Título del Learning Path: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Descripción del Learning Path: ");
                    String descripcion = scanner.nextLine();
                    System.out.print("Nivel de dificultad: ");
                    String nivelDificultad = scanner.nextLine();
                    sistema.crearLearningPath(profesor, titulo, descripcion, nivelDificultad);
                    System.out.println("Learning Path creado exitosamente.");
                    break;
                case 2:
                    verLearningPaths(sistema);
                    System.out.println("Seleccione un Learning Path para ver actividades o crearlas: ");
                    int opcionLp = scanner.nextInt();
                    learningPath lp = sistema.obtenerLearningPaths().get(opcionLp-1);
                    sistema.agregarActividad(profesor, lp);
                    
                    
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida, inténtalo nuevamente.");
            }
        }
    }

 // Método para gestionar opciones de estudiante
    private static void menuEstudiante(Estudiante estudiante, Sistema sistema, Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("---Menú Estudiante---");
            System.out.println("1. Ver Learning Paths");
            System.out.println("2. Cerrar sesión");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    verLearningPaths(sistema);
                    break;
                case 2:
                    salir = true;
                    break;
                default:
                    System.out.println("Seleccione una opción válida.");
            }
        }
    }

    private static void verLearningPaths(Sistema sistema) {
        System.out.println("=== Lista de Learning Paths ===");
        int i=0;
        
        for (learningPath lp : sistema.obtenerLearningPaths()) {
        	int num=i+1;
            System.out.println(num+". "+ lp.getTitulo() + " (" + lp.getNivelDificultad() + ")");
            System.out.println("  Descripción: " + lp.getDescripcion());
            i++;
        }

}
	
	

        
        
	
}
