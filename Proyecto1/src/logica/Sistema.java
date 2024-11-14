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
                    usuarios.add(usuario);  
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
    	
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
                String titulo = campos[0];
                String descripcion = campos[1];
                String nivelDificultad = campos[2];
                String loginCreador = campos[3];
                Profesor creador = (Profesor) buscarProfesorPorNombre(loginCreador);
                learningPath lp = new learningPath(titulo, descripcion, nivelDificultad, creador);
                learningPaths.add(lp);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar los Learning Paths: " + e.getMessage());
        }
    }
    
    
    public void agregarActividad(Profesor profesor, learningPath learningPath) {
    	
    	SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        formatoFecha.setLenient(false);
        String lpStr = learningPath.getTitulo();
        boolean esOpcionalQuiz;
        boolean esOpcionalTarea;
        boolean esOpcionalRecurso;
        ArrayList<String> opciones=new ArrayList<String>();
        

        if (learningPath != null && learningPath.getProfesor().equals(profesor)) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("¿Qué tipo de actividad deseas agregar? (1. Quiz, 2. Tarea, 3. Revisar Recurso)");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1: 
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
                    
                    System.out.println("Ingrese la pregunta: ");
                    String preguntaQuiz = scanner.nextLine();

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
                    Quiz quiz = new Quiz(nombreQuiz, descripcionQuiz, objetivoQuiz,nivelDificultadQuiz, duracionQuiz, fechaString,esOpcionalQuiz,prerequisitoSugerido,preguntaQuiz,numOpciones,opciones,iOpcionCorrecta);
                    learningPath.agregarActividad(quiz);
                    guardarActividadEnCSV(quiz,lpStr);
                    System.out.println("Quiz agregado con éxito.");
                    
                    break;
                case 2: 
                    System.out.println("Ingrese el título de la Tarea:");
                    String tituloTarea = scanner.nextLine();
                    System.out.println("Ingrese la descripción de la Tarea:");
                    String descripcionTarea = scanner.nextLine();
                    System.out.println("Ingrese el objetivo del Quiz:");
                    String objetivoTarea = scanner.nextLine();
                    System.out.println("Ingrese el nivel de dificultad:");
                    String nivelDificultadTarea = scanner.nextLine();
                    System.out.println("Ingrese la duración en minutos:");
                    int duracionTarea = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.println("Ingrese la fecha límite (dd/mm/yyyy):");
                    String fechaLimiteTarea = scanner.nextLine();
                    System.out.println("¿Es opcional? (si/no):");
                    String esOpcionalTareaTxt = scanner.nextLine();

                    if (esOpcionalTareaTxt.equals("si")) {
                        esOpcionalTarea = true;
                    } else {
                        esOpcionalTarea = false;
                    }
                    System.out.println("Ingrese las instrucciones de la Tarea:");
                    String instruccionesTarea = scanner.nextLine();
                    System.out.println("Ingrese el metodo de entrega:");
                    String metodoEntrega = scanner.nextLine();
                    Actividad prerequisitoSugeridoTarea=learningPath.getUltimaActividad();
                    Tarea tarea = new Tarea(tituloTarea, descripcionTarea, objetivoTarea, nivelDificultadTarea, duracionTarea, fechaLimiteTarea, esOpcionalTarea, prerequisitoSugeridoTarea,instruccionesTarea, metodoEntrega);
                    learningPath.agregarActividad(tarea);
                    guardarActividadEnCSV(tarea,lpStr);
                    System.out.println("Tarea agregada con éxito.");
                    
                    break;
                case 3: 
                    System.out.println("Ingrese el título del recurso:");
                    String tituloRecurso = scanner.nextLine();
                    System.out.println("Ingrese la descripción del recurso:");
                    String descripcionRecurso = scanner.nextLine();
                    System.out.println("Ingrese el objetivo de la revisión:");
                    String objetivoRecurso = scanner.nextLine();
                    System.out.println("Ingrese el nivel de dificultad:");
                    String nivelDificultadRecurso = scanner.nextLine();
                    System.out.println("Ingrese la duración en minutos:");
                    int duracionRecurso = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Ingrese la fecha límite (dd/mm/yyyy):");
                    String fechaRecurso = scanner.nextLine();
                    System.out.println("¿Es opcional? (si/no):");
                    String esOpcionalRecursoTxt = scanner.nextLine();
                    if (esOpcionalRecursoTxt.equals("si")) {
                        esOpcionalRecurso = true;
                    } else {
                        esOpcionalRecurso = false;
                    }
                    System.out.println("Ingrese el tipo de recurso (video, libro, pdf, etc.):");
                    String tipoRecurso = scanner.nextLine();
                    System.out.println("Ingrese el enlace al recurso:");
                    String enlaceRecurso = scanner.nextLine();
                    Actividad prerequisitoSugeridoRecurso=learningPath.getUltimaActividad();
                    RevisarRecurso recurso = new RevisarRecurso(tituloRecurso, descripcionRecurso, objetivoRecurso, nivelDificultadRecurso, duracionRecurso, fechaRecurso, esOpcionalRecurso,prerequisitoSugeridoRecurso, tipoRecurso, enlaceRecurso);
                    learningPath.agregarActividad(recurso);
                    guardarActividadEnCSV(recurso,lpStr);
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
    
    public void guardarActividadEnCSV(Actividad actividad, String tituloLearningPath) {
        String archivoActividades = "actividades.csv"; // Ruta del archivo CSV de actividades

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoActividades, true))) {
            StringBuilder sb = new StringBuilder();
            sb.append(tituloLearningPath).append(",");
            sb.append(actividad.getClass().getSimpleName()).append(",");
            sb.append(actividad.getNombre()).append(",");
            sb.append(actividad.getDescripcion()).append(",");
            sb.append(actividad.getObjetivo()).append(",");
            sb.append(actividad.getNivelDificultad()).append(",");
            sb.append(actividad.getDuracion()).append(",");
            sb.append(actividad.getFechaLimite()).append(",");
            sb.append(actividad.isEsOpcional());


            if (actividad instanceof Quiz) {
                Quiz quiz = (Quiz) actividad;
                sb.append(",").append(quiz.getPregunta());
                sb.append(",").append(quiz.getNumOpciones());
                sb.append(",").append(quiz.getIOpcionCorrecta());
                for (String op : quiz.getPreguntas()) {
                	sb.append(",").append(op);
                }
                
                
            } else if (actividad instanceof Tarea) {
                Tarea tarea = (Tarea) actividad;
                sb.append(",").append(tarea.getInstrucciones());
                sb.append(",").append(tarea.getMetodo());

            } else if (actividad instanceof RevisarRecurso) {
                RevisarRecurso recurso = (RevisarRecurso) actividad;
                sb.append(",").append(recurso.getTipoRecurso());
                sb.append(",").append(recurso.getEnlace());
            }

            bw.write(sb.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la actividad en el archivo CSV.");
            e.printStackTrace();
        }
    }
    
    public void cargarActividadesDesdeCSV() {
        String archivoActividades = "actividades.csv"; // Ruta del archivo CSV de actividades
        ArrayList<String> opciones = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivoActividades))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String tituloLearningPath = datos[0];
                String tipoActividad = datos[1];
                String tituloActividad = datos[2];
                String descripcion = datos[3];
                String objetivo = datos[4];
                String nivelDificultad = datos[5];
                int duracion = Integer.parseInt(datos[6]);
                String fechaLimite = datos[7];
                boolean esOpcional = Boolean.parseBoolean(datos[8]);
                

                learningPath learningPath = buscarLearningPathPorTitulo(tituloLearningPath);

                if (learningPath != null) {
                    if (tipoActividad.equals("Quiz")) {
                    	String pregunta = datos[9];
                    	int numOpciones = Integer.parseInt(datos[10]);
                    	int iOpcionCorrecta = Integer.parseInt(datos[11]);
                    	for (int i = 1; i <= numOpciones; i++) {
                    		opciones.add(datos[i+11]);
                    	}
                        Quiz quiz = new Quiz(tituloActividad, descripcion, objetivo, nivelDificultad, duracion, fechaLimite,esOpcional, null, pregunta, numOpciones, opciones, iOpcionCorrecta);
                        learningPath.agregarActividad(quiz);
                    } else if (tipoActividad.equals("Tarea")) {
                        String instrucciones = datos[9];
                        String metodo = datos[10];
                        Tarea tarea = new Tarea(tituloActividad, descripcion, objetivo, nivelDificultad, duracion, fechaLimite,esOpcional, null,instrucciones, metodo);
                        learningPath.agregarActividad(tarea);
                    } else if (tipoActividad.equals("RevisarRecurso")) {
                        String tipo = datos[9];
                        String enlace = datos[10];
                        RevisarRecurso revisarRecurso = new RevisarRecurso(tituloActividad, descripcion, objetivo, nivelDificultad, duracion, fechaLimite,esOpcional, null, tipo, enlace);
                        learningPath.agregarActividad(revisarRecurso);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer las actividades desde el archivo CSV.");
            e.printStackTrace();
        }
    }


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
    


