package presentacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logica.learningPath;
import logica.Profesor;
import logica.Sistema;

public class PCrearAct extends JPanel {
    private Sistema sistema;
    private learningPath learningPath;
    private JPanel panelDinamico;
    

    public PCrearAct(Sistema sistema, JFrame ventanaPrincipal, learningPath learningPath, Profesor profesor) {
        this.sistema = sistema;
        this.learningPath = learningPath;

        setLayout(new BorderLayout());

        // Título
        JLabel etiqueta = new JLabel("Crear Actividad para: " + learningPath.getTitulo(), SwingConstants.CENTER);
        etiqueta.setFont(new Font("Arial", Font.BOLD, 18));
        add(etiqueta, BorderLayout.NORTH);
        
        

        // Panel de botones para seleccionar el tipo de actividad
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton btnQuiz = new JButton("Crear Quiz");
        JButton btnTarea = new JButton("Crear Tarea");
        JButton btnRecurso = new JButton("Crear Recurso");
        JButton btnVolver = new JButton("Volver");

        panelBotones.add(btnQuiz);
        panelBotones.add(btnTarea);
        panelBotones.add(btnRecurso);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        // Panel dinámico para mostrar el formulario correspondiente
        panelDinamico = new JPanel();
        panelDinamico.setLayout(new BorderLayout());
        add(panelDinamico, BorderLayout.CENTER);

        // Acción para "Crear Quiz"
        btnQuiz.addActionListener(e -> mostrarFormularioQuiz());

        // Acción para "Crear Tarea"
        btnTarea.addActionListener(e -> mostrarFormularioTarea());

        // Acción para "Crear Recurso"
        btnRecurso.addActionListener(e -> mostrarFormularioRecurso());

        // Acción para "Volver"
        btnVolver.addActionListener(e -> {
            ventanaPrincipal.setContentPane(new PVerLearningPath(sistema, ventanaPrincipal, profesor));
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });
    }

    private void mostrarFormularioQuiz() {
        panelDinamico.removeAll();
        //panelDinamico.add(new FormularioQuiz(sistema, learningPath));
        panelDinamico.revalidate();
        panelDinamico.repaint();
    }

    private void mostrarFormularioTarea() {
        panelDinamico.removeAll();
        panelDinamico.add(new PCrearTarea(sistema, learningPath));
        panelDinamico.revalidate();
        panelDinamico.repaint();
    }

    private void mostrarFormularioRecurso() {
        panelDinamico.removeAll();
        panelDinamico.add(new PCrearRecurso(sistema, learningPath));
        panelDinamico.revalidate();
        panelDinamico.repaint();
    }
}

