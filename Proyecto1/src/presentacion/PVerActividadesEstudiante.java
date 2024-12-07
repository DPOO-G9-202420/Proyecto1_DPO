package presentacion;

import javax.swing.*;
import java.awt.*;
import logica.*;

public class PVerActividadesEstudiante extends JPanel {
    private Sistema sistema;
    private JFrame ventanaPrincipal;
    private learningPath learningPath;
    private Estudiante estudiante;

    public PVerActividadesEstudiante(Sistema sistema, JFrame ventanaPrincipal, learningPath learningPath, Estudiante estudiante) {
        this.sistema = sistema;
        this.ventanaPrincipal = ventanaPrincipal;
        this.learningPath = learningPath;
        this.estudiante=estudiante;

        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Actividades de: " + learningPath.getTitulo(), SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Panel central para mostrar actividades
        JTextArea actividadesArea = new JTextArea();
        actividadesArea.setEditable(false);

        if (learningPath.getActividades().isEmpty()) {
            actividadesArea.setText("No hay actividades disponibles en este Learning Path.");
        } else {
            for (Actividad actividad : learningPath.getActividades()) {
                actividadesArea.append(actividad.toString() + "\n\n");
            }
        }
        add(new JScrollPane(actividadesArea), BorderLayout.CENTER);

        // Botón de volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            ventanaPrincipal.setContentPane(new PEstudiante(sistema, ventanaPrincipal, estudiante));
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        add(btnVolver, BorderLayout.SOUTH);
    }
}
