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
        
        
        sistema.cargarUsuarioCSV();
        //sistema.registrarUsuario("d.vergara112", "dani2004", "profesor");
        //sistema.registrarUsuario("mc.aponte2", "macalister14", "profesor");
        //sistema.registrarUsuario("s.vergara", "roli2006", "estudiante");
        //sistema.registrarUsuario("l.moure", "luciamoure", "estudiante");
        
        while (!salir) {
            System.out.println("===== Learning Path Recommendation System =====");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consumir nueva línea

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
                    System.out.println("Opción no válida, inténtalo nuevamente.");
            }
        }

        scanner.close();
    }
	
	private static void registrarUsuario(Sistema sistema, Scanner scanner) {
        System.out.println("=== Registrar usuario ===");
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
        System.out.println("=== Iniciar sesión ===");
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
	
	
	

        
        
	
}
