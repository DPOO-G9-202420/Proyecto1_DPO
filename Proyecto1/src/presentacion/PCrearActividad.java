package presentacion;

import logica.Sistema;
import logica.learningPath;
import logica.Actividad;
import logica.Tarea;
import logica.RevisarRecurso;
import logica.Quiz;
import logica.Profesor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PCrearActividad extends JPanel {
    public PCrearActividad(Sistema sistema, JFrame ventanaPrincipal, learningPath learningPath, Profesor profesor) {
        setLayout(new BorderLayout());
        
        // Título
        JLabel lblTitulo = new JLabel("Crear Actividad", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel para el formulario
        JPanel panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(0, 2, 10, 10));
        
        // Campos comunes
        JLabel lblNombre = new JLabel("Nombre de la Actividad:");
        JTextField txtNombre = new JTextField();
        
        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextArea txtDescripcion = new JTextArea(3, 20);
        
        JLabel lblDificultad = new JLabel("Nivel de Dificultad:");
        JComboBox<String> comboDificultad = new JComboBox<>(new String[]{"Fácil", "Intermedio", "Difícil"});
        
        JLabel lblDuracion = new JLabel("Duración (minutos):");
        JTextField txtDuracion = new JTextField();
        
        JLabel lblOpcional = new JLabel("Es opcional?");
        JComboBox<String> comboOpcional = new JComboBox<>(new String[]{"true", "false"});
        

        // Selección del tipo de actividad
        JLabel lblTipo = new JLabel("Tipo de Actividad:");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Revisar Recurso", "Tarea", "Quiz"});
        
        // Panel dinámico para campos adicionales
        JPanel panelDinamico = new JPanel();
        panelDinamico.setLayout(new GridLayout(0, 2, 10, 10));

        // Listener para cambiar el formulario según el tipo de actividad
        comboTipo.addActionListener(e -> {
            panelDinamico.removeAll();
            String tipoSeleccionado = (String) comboTipo.getSelectedItem();
            
            if (tipoSeleccionado.equals("Revisar Recurso")) {
                JLabel lblEnlace = new JLabel("URL del Recurso:");
                JTextField txtEnlace = new JTextField();
                JLabel lblTipoR = new JLabel("Tipo de recurso");
                JTextField txtTipoR = new JTextField();
                panelDinamico.add(lblTipoR);
                panelDinamico.add(txtTipoR);
                panelDinamico.add(lblEnlace);
                panelDinamico.add(txtEnlace);
            } else if (tipoSeleccionado.equals("Tarea")) {
                JLabel lblEntrega = new JLabel("Método de Entrega:");
                JTextField txtEntrega = new JTextField();
                JLabel lblInstrucciones = new JLabel("Método de Entrega:");
                JTextField txtInstrucciones = new JTextField();                
                panelDinamico.add(lblEntrega);
                panelDinamico.add(txtEntrega);
                panelDinamico.add(lblInstrucciones);
                panelDinamico.add(txtInstrucciones);
            } else if (tipoSeleccionado.equals("Quiz")) {
                JLabel lblPreguntas = new JLabel("Número de Preguntas:");
                JTextField txtPreguntas = new JTextField();
                
                JLabel lblNotaAprobatoria = new JLabel("Nota Mínima para Aprobar:");
                JTextField txtNotaAprobatoria = new JTextField();
                
                panelDinamico.add(lblPreguntas);
                panelDinamico.add(txtPreguntas);
                panelDinamico.add(lblNotaAprobatoria);
                panelDinamico.add(txtNotaAprobatoria);
            }

            panelDinamico.revalidate();
            panelDinamico.repaint();
        });

        // Botones
        JButton btnGuardar = new JButton("Guardar Actividad");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                String dificultad = (String) comboDificultad.getSelectedItem();
                int duracion = Integer.parseInt(txtDuracion.getText());
                String tipo = (String) comboTipo.getSelectedItem();
                boolean esOpcional = (boolean) comboOpcional.getSelectedItem();

                Actividad nuevaActividad = null;

                if (tipo.equals("Recurso Educativo")) {
                    String tipoR = ((JTextField) panelDinamico.getComponent(1)).getText();
                    String recurso = ((JTextField) panelDinamico.getComponent(2)).getText();
                    nuevaActividad = new RevisarRecurso(nombre, descripcion,"objetivo", dificultad,duracion, "fecha", esOpcional, null, tipoR,recurso);
                } else if (tipo.equals("Tarea")) {
                    String medioEntrega = ((JTextField) panelDinamico.getComponent(1)).getText();
                    //nuevaActividad = new Tarea(nombre, descripcion, dificultad, duracion, medioEntrega);
                } else if (tipo.equals("Quiz")) {
                    int numPreguntas = Integer.parseInt(((JTextField) panelDinamico.getComponent(1)).getText());
                    int notaAprobatoria = Integer.parseInt(((JTextField) panelDinamico.getComponent(3)).getText());
                    //nuevaActividad = new Quiz(nombre, descripcion, dificultad, duracion, numPreguntas, notaAprobatoria);
                }

                if (nuevaActividad != null) {
                    learningPath.agregarActividad(nuevaActividad);
                    sistema.guardarActividadEnCSV(nuevaActividad,learningPath.getTitulo()); // Guardar en CSV
                    JOptionPane.showMessageDialog(this, "Actividad creada con éxito.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al crear la actividad: " + ex.getMessage());
            }
        });

        btnCancelar.addActionListener(e -> {
            // Volver a la pantalla de la lista de Learning Paths
            ventanaPrincipal.setContentPane(new PProfesor(profesor,sistema, ventanaPrincipal));
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        // Agregar elementos al panel principal
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblDescripcion);
        panelFormulario.add(new JScrollPane(txtDescripcion));
        panelFormulario.add(lblDificultad);
        panelFormulario.add(comboDificultad);
        panelFormulario.add(lblDuracion);
        panelFormulario.add(txtDuracion);
        panelFormulario.add(lblOpcional);
        panelFormulario.add(comboOpcional);
        panelFormulario.add(lblTipo);
        panelFormulario.add(comboTipo);

        // Botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

        // Agregar todo al panel principal
        add(panelFormulario, BorderLayout.CENTER);
        add(panelDinamico, BorderLayout.SOUTH);
        add(panelBotones, BorderLayout.PAGE_END);
    }
}

