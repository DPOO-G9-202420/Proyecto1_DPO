package presentacion;

import javax.swing.*;
import java.awt.*;
import logica.*;

public class PEstudiante extends JPanel {
    private Sistema sistema;
    private JFrame ventanaPrincipal;
    private Estudiante estudiante;

    public PEstudiante(Sistema sistema, JFrame ventanaPrincipal, Estudiante estudiante) {
        this.sistema = sistema;
        this.ventanaPrincipal = ventanaPrincipal;
        this.estudiante = estudiante;

        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Bienvenido, " + estudiante.getLogin(), SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(titulo, BorderLayout.NORTH);

        // Panel central con lista de Learning Paths
        DefaultListModel<learningPath> modeloLista = new DefaultListModel<>();
        for (learningPath lp : sistema.obtenerLearningPaths()) {
            modeloLista.addElement(lp);
        }

        JList<learningPath> listaLearningPaths = new JList<>(modeloLista);
        listaLearningPaths.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaLearningPaths);

        // Botón para ver actividades
        JButton btnVerActividades = new JButton("Ver Actividades");
        btnVerActividades.addActionListener(e -> {
            learningPath seleccionado = listaLearningPaths.getSelectedValue();
            if (seleccionado == null) {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un Learning Path.", "Error", JOptionPane.WARNING_MESSAGE);
            } else {
                ventanaPrincipal.setContentPane(new PVerActividadesEstudiante(sistema, ventanaPrincipal, seleccionado, estudiante));
                ventanaPrincipal.revalidate();
                ventanaPrincipal.repaint();
            }
        });

        // Botón de cerrar sesión
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(e -> {
            ventanaPrincipal.setContentPane(new PLogin(sistema, ventanaPrincipal));
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(btnVerActividades);
        panelBotones.add(btnCerrarSesion);

        // Añadir componentes al panel principal
        add(scrollPane, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
