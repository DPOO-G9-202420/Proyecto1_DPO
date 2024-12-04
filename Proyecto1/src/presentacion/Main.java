package presentacion;

import javax.swing.SwingUtilities;

import logica.Sistema;

public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.cargarUsuariosCSV();
        sistema.cargarLearningPathsDesdeCSV();
        sistema.cargarActividadesDesdeCSV();

        SwingUtilities.invokeLater(() -> {
            VentPrincipal ventana = new VentPrincipal(sistema);
            ventana.setVisible(true);
        });
    }
}