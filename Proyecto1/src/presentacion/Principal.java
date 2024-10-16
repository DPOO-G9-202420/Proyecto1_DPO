package presentacion;

import logica.Sistema;

import java.io.IOException;
import java.util.ArrayList;

public class Principal {
	public static void main(String[] args) throws IOException {
        Sistema sistema = new Sistema();
        
        sistema.registrarUsuario("d.vergara112", "dani2004", "profesor");
        sistema.registrarUsuario("mc.aponte2", "macalister14", "profesor");
        
	}
}
