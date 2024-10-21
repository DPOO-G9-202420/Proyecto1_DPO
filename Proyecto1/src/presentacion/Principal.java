package presentacion;

import logica.Sistema;
import logica.Usuario;
import logica.Profesor;
import logica.Estudiante;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Principal {
	public static void main(String[] args) throws IOException {
        Sistema sistema = new Sistema();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        
        
        sistema.cargarUsuariosCSV();
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
                gestionarProfesor((Profesor) usuario, sistema, scanner);
            } else if (usuario instanceof Estudiante) {
                gestionarEstudiante((Estudiante) usuario, sistema, scanner);
            }
        } else {
            System.out.println("Credenciales incorrectas. Inténtalo nuevamente.");
        }
    }
	
	// Método para gestionar opciones de profesor
    private static void gestionarProfesor(Profesor profesor, Sistema sistema, Scanner scanner) {
        boolean salir = false;

        while (!salir) {
            System.out.println("---Menú Profesor---");
            System.out.println("1. Crear Learning Path");
            System.out.println("2. Ver Learning Paths");
            System.out.println("3. Cerrar sesión");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

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
    private static void gestionarEstudiante(Estudiante estudiante, Sistema sistema, Scanner scanner) {
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

    // Método para mostrar todos los Learning Paths
    /*
    private static void verLearningPaths(Sistema sistema) {
        System.out.println("=== Lista de Learning Paths ===");
        for (LearningPath lp : sistema.obtenerLearningPaths()) {
            System.out.println("- " + lp.getTitulo() + " (" + lp.getNivelDificultad() + ")");
            System.out.println("  Descripción: " + lp.getDescripcion());
        }
    }
}
	
	*/
	

        
        
	
}
