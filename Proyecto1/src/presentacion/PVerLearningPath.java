package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logica.Profesor;
import logica.Sistema;
import logica.learningPath;

public class PVerLearningPath extends JPanel {
    private Sistema sistema;
    private Profesor profesor;

    public PVerLearningPath(Sistema sistema, JFrame ventanaPrincipal, Profesor profesor) {
        this.sistema = sistema;
        this.profesor=profesor;
        setLayout(new BorderLayout());

        // Título
        JLabel etiqueta = new JLabel("Ver Learning Paths", SwingConstants.CENTER);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
        add(etiqueta, BorderLayout.NORTH);

        // Lista de Learning Paths
        java.util.List<learningPath> learningPaths = sistema.obtenerLearningPaths();
        DefaultListModel<String> modeloLista = new DefaultListModel<>();
        JList<String> listaLearningPaths = new JList<>(modeloLista);

        for (learningPath learningPath : learningPaths) {
            modeloLista.addElement(learningPath.getTitulo());
        }

        listaLearningPaths.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaLearningPaths);
        add(scrollPane, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton btnSeleccionar = new JButton("Seleccionar");
        JButton btnVolver = new JButton("Volver");

        // Acción al seleccionar un Learning Path
        btnSeleccionar.addActionListener(e -> {
            int indiceSeleccionado = listaLearningPaths.getSelectedIndex();
            if (indiceSeleccionado != -1) {
                learningPath learningPathSeleccionado = learningPaths.get(indiceSeleccionado);
                ventanaPrincipal.setContentPane(new PCrearAct(sistema, ventanaPrincipal, learningPathSeleccionado,profesor));
                ventanaPrincipal.revalidate();
                ventanaPrincipal.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un Learning Path de la lista.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Acción para volver al menú anterior
        btnVolver.addActionListener(e -> {
            ventanaPrincipal.setContentPane(new PProfesor(profesor, sistema, ventanaPrincipal));
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        panelBotones.add(btnSeleccionar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }
}
