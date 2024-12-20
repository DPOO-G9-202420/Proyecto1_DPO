package presentacion;

import logica.Sistema;
import logica.Usuario;
import logica.Profesor;
import logica.Actividad;
import logica.Estudiante;
import logica.learningPath;

import java.awt.desktop.SystemEventListener;
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
        sistema.cargarActividadesDesdeCSV();
        
        
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
                    System.out.println("\nSeleccione un Learning Path para ver o crear actividades: ");
                    int opcionLp = scanner.nextInt();
                    learningPath lp = sistema.obtenerLearningPaths().get(opcionLp-1);
                    System.out.println("\nEscriba 1 para ver las actividades o 2 para agregar una: ");
                    int op = scanner.nextInt();
                    if (op==1) {
                    	mostrarActividadesDeLearningPath(lp);
                    }
                    else if(op==2) {
                    	sistema.agregarActividad(profesor, lp);
                    }
                    else {mostrarActividadesDeLearningPath(lp);}
                    
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida, inténtalo nuevamente.");
            }
        }
    }

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
                    System.out.println("\nSeleccione un Learning Path para ver actividades: ");
                    int opcionLp = scanner.nextInt();
                    learningPath lp = sistema.obtenerLearningPaths().get(opcionLp-1);
                    mostrarActividadesDeLearningPath(lp);
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
	
    private static void mostrarActividadesDeLearningPath(learningPath learningPath) {
        System.out.println("\nActividades en el Learning Path: " + learningPath.getTitulo());

        if (learningPath.getActividades().isEmpty()) {
            System.out.println("Este Learning Path no tiene actividades.");
            return;
        }

        for (int i = 0; i < learningPath.getActividades().size(); i++) {
            Actividad actividad = learningPath.getActividades().get(i);
            System.out.println((i + 1) + ". " + actividad.getNombre() + " - " + actividad.getDescripcion() +
                    " (" + actividad.getTipo() + ")");
            
        }
        System.out.println("");
    }

        
        
	
}
