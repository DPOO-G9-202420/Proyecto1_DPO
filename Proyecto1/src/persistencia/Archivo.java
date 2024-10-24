package persistencia;
import logica.Profesor;
import logica.Sistema;
import logica.Usuario;
import logica.learningPath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Archivo {

	public static void guardarTextoCSV(ArrayList<String> textos, String nombreArchivo) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));
		for(String texto : textos) {
			bw.write(texto);
			bw.write(",");
			//ARREGLAR NO SIRVE

		}
		bw.close();
	}
	
	public static void escribirUsuarioCSV(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("usuarios.csv", true))) {
            String tipo = usuario.getTipoUsuario();
            String linea = usuario.getLogin() + "," + usuario.getContrasena() + "," + tipo;
            writer.write(linea);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo CSV: " + e.getMessage());
        }
    }
	
	// Método para guardar un Learning Path individualmente en un archivo CSV (sin actividades)
    public static void guardarLearningPathEnCSV(learningPath lp) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("lp.csv", true))) {
            // Escribir los datos del Learning Path en el archivo CSV (sin las actividades)
            writer.write(lp.getTitulo() + "," +
                         lp.getDescripcion() + "," +
                         lp.getNivelDificultad() + "," +
                         lp.getProfesor().getLogin() + "\n");
        } catch (IOException e) {
            System.out.println("Error al guardar el Learning Path: " + e.getMessage());
        }
    }
    
    
	

    
	public static ArrayList<String> leerArchivoCSV(String nombreArchivo) throws IOException{
		ArrayList<String> textos = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
		String linea;
		while((linea=br.readLine()) != null) {
			textos.add(linea);
		}
		br.close();
		return textos;
		
	}
}