package presentacion;

import javax.swing.*;
import java.awt.*;
import logica.*;

public class PVerActividades extends JPanel {
    private Sistema sistema;
    private JFrame ventanaPrincipal;
    private learningPath learningPath;

    public PVerActividades(Sistema sistema, JFrame ventanaPrincipal, learningPath learningPath) {
        this.sistema = sistema;
        this.ventanaPrincipal = ventanaPrincipal;
        this.learningPath = learningPath;

        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Actividades de: " + learningPath.getTitulo(), SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Panel central para mostrar actividades
        JTextArea actividadesArea = new JTextArea();
        actividadesArea.setEditable(false);

        if (learningPath.getActividades().isEmpty()) {
            actividadesArea.setText("No hay actividades registradas en este Learning Path.");
        } else {
            for (Actividad actividad : learningPath.getActividades()) {
                actividadesArea.append(actividad.toString() + "\n\n");
            }
        }
        add(new JScrollPane(actividadesArea), BorderLayout.CENTER);

        // Botón de volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(e -> {
            ventanaPrincipal.setContentPane(new PVerLearningPath(sistema, ventanaPrincipal, learningPath.getProfesor()));
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        add(btnVolver, BorderLayout.SOUTH);
    }
}

