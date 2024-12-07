package presentacion;

import javax.swing.*;
import java.awt.*;
import logica.learningPath;
import logica.Tarea;
import logica.Sistema;

public class PCrearTarea extends JPanel {
    public PCrearTarea(Sistema sistema, learningPath learningPath) {
        setLayout(new GridLayout(4, 2, 10, 10));

     // Campos para ingresar datos de la tarea
        add(new JLabel("Nombre de la tarea:"));
        JTextField txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Descripción de la tarea:"));
        JTextField txtDescripcion = new JTextField();
        add(txtDescripcion);
        
        JLabel lblDificultad = new JLabel("Nivel de Dificultad:");
        JComboBox<String> comboDificultad = new JComboBox<>(new String[]{"Fácil", "Intermedio", "Difícil"});
        add(lblDificultad);
        add(comboDificultad);
        
        JLabel lblDuracion = new JLabel("Duración (minutos):");
        JTextField txtDuracion = new JTextField();
        add(lblDuracion);
        add(txtDuracion);
        
        JLabel lblOpcional = new JLabel("Es opcional?");
        JComboBox<String> comboOpcional = new JComboBox<>(new String[]{"SI", "NO"});
        add(lblOpcional);
        add(comboOpcional);
        
        JLabel lblInstrucciones = new JLabel("Ingrese las instrucciones de la Tarea:");
        JTextField txtInstrucciones = new JTextField();
        add(lblInstrucciones);
        add(txtInstrucciones);
        
        JLabel lblMetodo = new JLabel("Ingrese el metodo de entrega:");
        JTextField txtMetodo = new JTextField();
        add(lblMetodo);
        add(txtMetodo);
        

        JButton btnGuardar = new JButton("Guardar");
        add(btnGuardar);

        // Espacio vacío para centrar el botón
        add(new JLabel(""));

        btnGuardar.addActionListener(e -> {
            try {
            	boolean esOpcional;
            	String nombre = txtNombre.getText();
                String descripcion = txtDescripcion.getText();
                String dificultad = (String) comboDificultad.getSelectedItem();
                int duracion = Integer.parseInt(txtDuracion.getText());
                String esOpcionalS = (String) comboOpcional.getSelectedItem();
                if (esOpcionalS.equals("SI")) {
                	esOpcional=true;
                }
                else {esOpcional=false;}
                
                String ins = txtInstrucciones.getText();
                String met = txtMetodo.getText();

                Tarea tarea = new Tarea(nombre, descripcion,"objetivo", dificultad,duracion, "fecha", esOpcional, null, ins,met);
                learningPath.agregarActividad(tarea);
                sistema.guardarActividadEnCSV(tarea,learningPath.getTitulo());

                JOptionPane.showMessageDialog(this, "Tarea agregada exitosamente.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese valores válidos.");
            }
        });
    }
}

